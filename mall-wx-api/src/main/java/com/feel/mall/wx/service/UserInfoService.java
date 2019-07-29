package com.feel.mall.wx.service;

import com.feel.mall.db.domain.MallUser;
import com.feel.mall.db.service.MallUserService;
import com.feel.mall.wx.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService {
    @Autowired
    private MallUserService userService;


    public UserInfo getInfo(Integer userId) {
        MallUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        return userInfo;
    }
}
