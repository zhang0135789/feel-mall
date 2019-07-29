package com.feel.mall.wx.web;

import com.feel.mall.wx.annotation.LoginUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.feel.mall.core.util.ResponseUtil;
import com.feel.mall.db.domain.MallUser;
import com.feel.mall.db.domain.MallUserFormid;
import com.feel.mall.db.service.MallUserFormIdService;
import com.feel.mall.db.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/wx/formid")
@Validated
public class WxUserFormId {
    private final Log logger = LogFactory.getLog(WxUserFormId.class);

    @Autowired
    private MallUserService userService;

    @Autowired
    private MallUserFormIdService formIdService;

    @GetMapping("create")
    public Object create(@LoginUser Integer userId, @NotNull String formId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        MallUser user = userService.findById(userId);
        MallUserFormid userFormid = new MallUserFormid();
        userFormid.setOpenid(user.getWeixinOpenid());
        userFormid.setFormid(formId);
        userFormid.setIsprepay(false);
        userFormid.setUseamount(1);
        userFormid.setExpireTime(LocalDateTime.now().plusDays(7));
        formIdService.addUserFormid(userFormid);

        return ResponseUtil.ok();
    }
}
