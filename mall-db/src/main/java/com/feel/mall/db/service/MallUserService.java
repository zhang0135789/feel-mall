package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallUserMapper;
import com.feel.mall.db.domain.MallUser;
import com.feel.mall.db.domain.MallUserExample;
import com.feel.mall.db.domain.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallUserService {
    @Resource
    private MallUserMapper mallUserMapper;

    public MallUser findById(Integer userId) {
        return mallUserMapper.selectByPrimaryKey(userId);
    }

    public UserVo findUserVoById(Integer userId) {
        MallUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    public MallUser queryByOid(String openId) {
        MallUserExample example = new MallUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return mallUserMapper.selectOneByExample(example);
    }

    public void add(MallUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        mallUserMapper.insertSelective(user);
    }

    public int updateById(MallUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return mallUserMapper.updateByPrimaryKeySelective(user);
    }

    public List<MallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        MallUserExample example = new MallUserExample();
        MallUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallUserMapper.selectByExample(example);
    }

    public int count() {
        MallUserExample example = new MallUserExample();
        example.or().andDeletedEqualTo(false);

        return (int) mallUserMapper.countByExample(example);
    }

    public List<MallUser> queryByUsername(String username) {
        MallUserExample example = new MallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return mallUserMapper.selectByExample(example);
    }

    public boolean checkByUsername(String username) {
        MallUserExample example = new MallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return mallUserMapper.countByExample(example) != 0;
    }

    public List<MallUser> queryByMobile(String mobile) {
        MallUserExample example = new MallUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return mallUserMapper.selectByExample(example);
    }

    public List<MallUser> queryByOpenid(String openid) {
        MallUserExample example = new MallUserExample();
        example.or().andWeixinOpenidEqualTo(openid).andDeletedEqualTo(false);
        return mallUserMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        mallUserMapper.logicalDeleteByPrimaryKey(id);
    }
}
