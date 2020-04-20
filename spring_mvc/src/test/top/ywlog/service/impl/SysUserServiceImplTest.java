package top.ywlog.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ywlog.entity.SysUser;
import top.ywlog.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class SysUserServiceImplTest
{

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void update()
    {
        SysUser sysUser = new SysUser();
        sysUser.setId(24L);
        sysUserService.update(sysUser);
    }
}