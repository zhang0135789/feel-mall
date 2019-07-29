package com.feel.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallRoleMapper;
import com.feel.mall.db.domain.MallRole;
import com.feel.mall.db.domain.MallRoleExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MallRoleService {
    @Resource
    private MallRoleMapper mallRoleMapper;


    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        MallRoleExample example = new MallRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<MallRole> roleList = mallRoleMapper.selectByExample(example);

        for(MallRole role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<MallRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        MallRoleExample example = new MallRoleExample();
        MallRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallRoleMapper.selectByExample(example);
    }

    public MallRole findById(Integer id) {
        return mallRoleMapper.selectByPrimaryKey(id);
    }

    public void add(MallRole role) {
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        mallRoleMapper.insertSelective(role);
    }

    public void deleteById(Integer id) {
        mallRoleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(MallRole role) {
        role.setUpdateTime(LocalDateTime.now());
        mallRoleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        MallRoleExample example = new MallRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return mallRoleMapper.countByExample(example) != 0;
    }

    public List<MallRole> queryAll() {
        MallRoleExample example = new MallRoleExample();
        example.or().andDeletedEqualTo(false);
        return mallRoleMapper.selectByExample(example);
    }
}
