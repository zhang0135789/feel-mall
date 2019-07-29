package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallFootprintMapper;
import com.feel.mall.db.domain.MallFootprint;
import com.feel.mall.db.domain.MallFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallFootprintService {
    @Resource
    private MallFootprintMapper mallFootprintMapper;

    public List<MallFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        MallFootprintExample example = new MallFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(MallFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return mallFootprintMapper.selectByExample(example);
    }

    public MallFootprint findById(Integer id) {
        return mallFootprintMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        mallFootprintMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallFootprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        mallFootprintMapper.insertSelective(footprint);
    }

    public List<MallFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        MallFootprintExample example = new MallFootprintExample();
        MallFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallFootprintMapper.selectByExample(example);
    }
}
