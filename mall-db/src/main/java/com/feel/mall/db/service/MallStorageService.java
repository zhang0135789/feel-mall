package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallStorageMapper;
import com.feel.mall.db.domain.MallStorage;
import com.feel.mall.db.domain.MallStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallStorageService {
    @Autowired
    private MallStorageMapper mallStorageMapper;

    public void deleteByKey(String key) {
        MallStorageExample example = new MallStorageExample();
        example.or().andKeyEqualTo(key);
        mallStorageMapper.logicalDeleteByExample(example);
    }

    public void add(MallStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        mallStorageMapper.insertSelective(storageInfo);
    }

    public MallStorage findByKey(String key) {
        MallStorageExample example = new MallStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return mallStorageMapper.selectOneByExample(example);
    }

    public int update(MallStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return mallStorageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public MallStorage findById(Integer id) {
        return mallStorageMapper.selectByPrimaryKey(id);
    }

    public List<MallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        MallStorageExample example = new MallStorageExample();
        MallStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallStorageMapper.selectByExample(example);
    }
}
