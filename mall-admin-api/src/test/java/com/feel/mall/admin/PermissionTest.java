package com.feel.mall.admin;

import com.feel.mall.admin.util.Permission;
import com.feel.mall.admin.util.PermissionUtil;
import com.feel.mall.admin.vo.PermVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.feel.mall.admin.util.Permission;
import com.feel.mall.admin.util.PermissionUtil;
import com.feel.mall.admin.vo.PermVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PermissionTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        final String basicPackage = "com.feel.mall.admin";
        List<Permission> permissionList = PermissionUtil.listPermission(context, basicPackage);
        List<PermVo> permVoList = PermissionUtil.listPermVo(permissionList);
        permVoList.stream().forEach(System.out::println);
    }
}
