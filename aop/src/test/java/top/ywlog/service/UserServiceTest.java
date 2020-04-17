package top.ywlog.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class UserServiceTest
{
    private static UserService userService;

    @Before
    public void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
        userService = context.getBean("userService", UserService.class);
    }

    @Test
    public void addUserTest()
    {
        System.out.println("操作结果：" + userService.addUser());
    }

    @Test
    public void updateTest()
    {
        System.out.println("操作结果：" + userService.updateUser());
    }

    @Test
    public void editUserTest()
    {
        Assert.assertTrue(userService.editUser());
    }

    @Test
    public void deleteUserTest()
    {
        Assert.assertTrue(userService.deleteUser());
    }

    @Test
    public void insertUserTest()
    {
        Assert.assertTrue(userService.insertUser());
    }
}