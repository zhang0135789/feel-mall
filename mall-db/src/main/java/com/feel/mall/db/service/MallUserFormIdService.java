package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallUserFormidMapper;
import com.feel.mall.db.domain.MallUserFormid;
import com.feel.mall.db.domain.MallUserFormidExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class MallUserFormIdService {
    @Resource
    private MallUserFormidMapper mallUserFormidMapper;

    /**
     * 查找是否有可用的FormId
     *
     * @param openId
     * @return
     */
    public MallUserFormid queryByOpenId(String openId) {
        MallUserFormidExample example = new MallUserFormidExample();
        //符合找到该用户记录，且可用次数大于1，且还未过期
        example.or().andOpenidEqualTo(openId).andExpireTimeGreaterThan(LocalDateTime.now());
        return mallUserFormidMapper.selectOneByExample(example);
    }

    /**
     * 更新或删除FormId
     *
     * @param userFormid
     */
    public int updateUserFormId(MallUserFormid userFormid) {
        //更新或者删除缓存
        if (userFormid.getIsprepay() && userFormid.getUseamount() > 1) {
            userFormid.setUseamount(userFormid.getUseamount() - 1);
            userFormid.setUpdateTime(LocalDateTime.now());
            return mallUserFormidMapper.updateByPrimaryKey(userFormid);
        } else {
            return mallUserFormidMapper.deleteByPrimaryKey(userFormid.getId());
        }
    }

    /**
     * 添加一个 FormId
     *
     * @param userFormid
     */
    public void addUserFormid(MallUserFormid userFormid) {
        userFormid.setAddTime(LocalDateTime.now());
        userFormid.setUpdateTime(LocalDateTime.now());
        mallUserFormidMapper.insertSelective(userFormid);
    }
}
