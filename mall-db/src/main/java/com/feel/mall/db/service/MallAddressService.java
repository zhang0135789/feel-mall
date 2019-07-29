package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallAddressMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallAddress;
import com.feel.mall.db.domain.MallAddressExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallAddressService {
    @Resource
    private MallAddressMapper mallAddressMapper;

    public List<MallAddress> queryByUid(Integer uid) {
        MallAddressExample example = new MallAddressExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        return mallAddressMapper.selectByExample(example);
    }

    public MallAddress query(Integer userId, Integer id) {
        MallAddressExample example = new MallAddressExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return mallAddressMapper.selectOneByExample(example);
    }

    public int add(MallAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return mallAddressMapper.insertSelective(address);
    }

    public int update(MallAddress address) {
        address.setUpdateTime(LocalDateTime.now());
        return mallAddressMapper.updateByPrimaryKeySelective(address);
    }

    public void delete(Integer id) {
        mallAddressMapper.logicalDeleteByPrimaryKey(id);
    }

    public MallAddress findDefault(Integer userId) {
        MallAddressExample example = new MallAddressExample();
        example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return mallAddressMapper.selectOneByExample(example);
    }

    public void resetDefault(Integer userId) {
        MallAddress address = new MallAddress();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        MallAddressExample example = new MallAddressExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        mallAddressMapper.updateByExampleSelective(address, example);
    }

    public List<MallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        MallAddressExample example = new MallAddressExample();
        MallAddressExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallAddressMapper.selectByExample(example);
    }
}
