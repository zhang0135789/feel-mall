package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallCollectMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallCollect;
import com.feel.mall.db.domain.MallCollectExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallCollectService {
    @Resource
    private MallCollectMapper mallCollectMapper;

    public int count(int uid, Integer gid) {
        MallCollectExample example = new MallCollectExample();
        example.or().andUserIdEqualTo(uid).andValueIdEqualTo(gid).andDeletedEqualTo(false);
        return (int) mallCollectMapper.countByExample(example);
    }

    public List<MallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order) {
        MallCollectExample example = new MallCollectExample();
        MallCollectExample.Criteria criteria = example.createCriteria();

        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        criteria.andUserIdEqualTo(userId);
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallCollectMapper.selectByExample(example);
    }

    public int countByType(Integer userId, Byte type) {
        MallCollectExample example = new MallCollectExample();
        example.or().andUserIdEqualTo(userId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return (int) mallCollectMapper.countByExample(example);
    }

    public MallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        MallCollectExample example = new MallCollectExample();
        example.or().andUserIdEqualTo(userId).andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        return mallCollectMapper.selectOneByExample(example);
    }

    public void deleteById(Integer id) {
        mallCollectMapper.logicalDeleteByPrimaryKey(id);
    }

    public int add(MallCollect collect) {
        collect.setAddTime(LocalDateTime.now());
        collect.setUpdateTime(LocalDateTime.now());
        return mallCollectMapper.insertSelective(collect);
    }

    public List<MallCollect> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        MallCollectExample example = new MallCollectExample();
        MallCollectExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallCollectMapper.selectByExample(example);
    }
}
