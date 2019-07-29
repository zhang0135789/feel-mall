package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallAdminMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallAdmin;
import com.feel.mall.db.domain.MallAdmin.Column;
import com.feel.mall.db.domain.MallAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallAdminService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};
    @Resource
    private MallAdminMapper mallAdminMapper;

    public List<MallAdmin> findAdmin(String username) {
        MallAdminExample example = new MallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return mallAdminMapper.selectByExample(example);
    }

    public MallAdmin findAdmin(Integer id) {
        return mallAdminMapper.selectByPrimaryKey(id);
    }

    public List<MallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        MallAdminExample example = new MallAdminExample();
        MallAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallAdminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(MallAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return mallAdminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        mallAdminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        mallAdminMapper.insertSelective(admin);
    }

    public MallAdmin findById(Integer id) {
        return mallAdminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<MallAdmin> all() {
        MallAdminExample example = new MallAdminExample();
        example.or().andDeletedEqualTo(false);
        return mallAdminMapper.selectByExample(example);
    }
}
