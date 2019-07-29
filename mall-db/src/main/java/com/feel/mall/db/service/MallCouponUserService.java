package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallCouponUserMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallCouponUser;
import com.feel.mall.db.domain.MallCouponUserExample;
import com.feel.mall.db.util.CouponUserConstant;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallCouponUserService {
    @Resource
    private MallCouponUserMapper mallCouponUserMapper;

    public Integer countCoupon(Integer couponId) {
        MallCouponUserExample example = new MallCouponUserExample();
        example.or().andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int)mallCouponUserMapper.countByExample(example);
    }

    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        MallCouponUserExample example = new MallCouponUserExample();
        example.or().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId).andDeletedEqualTo(false);
        return (int)mallCouponUserMapper.countByExample(example);
    }

    public void add(MallCouponUser couponUser) {
        couponUser.setAddTime(LocalDateTime.now());
        couponUser.setUpdateTime(LocalDateTime.now());
        mallCouponUserMapper.insertSelective(couponUser);
    }

    public List<MallCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer size, String sort, String order) {
        MallCouponUserExample example = new MallCouponUserExample();
        MallCouponUserExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if(couponId != null){
            criteria.andCouponIdEqualTo(couponId);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
            PageHelper.startPage(page, size);
        }

        return mallCouponUserMapper.selectByExample(example);
    }

    public List<MallCouponUser> queryAll(Integer userId, Integer couponId) {
        return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public List<MallCouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public MallCouponUser queryOne(Integer userId, Integer couponId) {
        List<MallCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
        if(couponUserList.size() == 0){
            return null;
        }
        return couponUserList.get(0);
    }

    public MallCouponUser findById(Integer id) {
        return mallCouponUserMapper.selectByPrimaryKey(id);
    }


    public int update(MallCouponUser couponUser) {
        couponUser.setUpdateTime(LocalDateTime.now());
        return mallCouponUserMapper.updateByPrimaryKeySelective(couponUser);
    }

    public List<MallCouponUser> queryExpired() {
        MallCouponUserExample example = new MallCouponUserExample();
        example.or().andStatusEqualTo(CouponUserConstant.STATUS_USABLE).andEndTimeLessThan(LocalDateTime.now()).andDeletedEqualTo(false);
        return mallCouponUserMapper.selectByExample(example);
    }
}
