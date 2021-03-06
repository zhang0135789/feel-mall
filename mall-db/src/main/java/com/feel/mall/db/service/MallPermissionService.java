package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallPermissionMapper;
import com.feel.mall.db.domain.MallPermission;
import com.feel.mall.db.domain.MallPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MallPermissionService {
    @Resource
    private MallPermissionMapper mallPermissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        MallPermissionExample example = new MallPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<MallPermission> permissionList = mallPermissionMapper.selectByExample(example);

        for(MallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        MallPermissionExample example = new MallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<MallPermission> permissionList = mallPermissionMapper.selectByExample(example);

        for(MallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        MallPermissionExample example = new MallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return mallPermissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        MallPermissionExample example = new MallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        mallPermissionMapper.logicalDeleteByExample(example);
    }

    public void add(MallPermission mallPermission) {
        mallPermission.setAddTime(LocalDateTime.now());
        mallPermission.setUpdateTime(LocalDateTime.now());
        mallPermissionMapper.insertSelective(mallPermission);
    }
}
