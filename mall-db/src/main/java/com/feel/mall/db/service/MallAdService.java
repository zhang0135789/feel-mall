package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallAdMapper;
import com.feel.mall.db.domain.MallAd;
import com.feel.mall.db.domain.MallAdExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallAdService {
    @Resource
    private MallAdMapper mallAdMapper;

    public List<MallAd> queryIndex() {
        MallAdExample example = new MallAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return mallAdMapper.selectByExample(example);
    }

    public List<MallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        MallAdExample example = new MallAdExample();
        MallAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallAdMapper.selectByExample(example);
    }

    public int updateById(MallAd ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return mallAdMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        mallAdMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallAd ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        mallAdMapper.insertSelective(ad);
    }

    public MallAd findById(Integer id) {
        return mallAdMapper.selectByPrimaryKey(id);
    }
}
