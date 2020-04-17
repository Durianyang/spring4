package top.ywlog.mycache.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ywlog.mycache.entity.User;

/**
 * @author Durian
 * @Description:
 * SpringCache测试 @CachePut注解每次使用方法返回值更新缓存
 * 可以使用CacheConfig注解再类上开启类级别缓存,这样方法上的直接参数配置就不必重复配置了
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:myCacheApplication.xml"})
public class UserServiceTest
{
    @Autowired
    private UserService userService;

    @Test
    public void findById()
    {
        String id = "2020";
        User user1 = userService.findById(id);
        User user2 = userService.findById(id);
        // 引用一致
        Assert.assertSame(user1, user2);

        userService.removeCache(id);

        User user3 = userService.findById(id);
        // 引用不一致
        Assert.assertNotSame(user1, user3);
    }

    @Test
    public void getOneTest()
    {
        String id = "2020";
        User one1 = userService.getOne(id);
        // 第二次直接从缓存中查询数据，且不执行方法体
        User one2 = userService.getOne(id);
        // 引用一致
        Assert.assertSame(one1, one2);
    }

    @Test
    public void updateUserTest()
    {
        String id = "2020";
        User user1 = userService.getOne(id);
        User user2 = userService.getOne(id);
        // 引用一致
        Assert.assertSame(user1, user2);

        // 在update方法上使用CachePut注解，使得每次更新数据后，使用新的返回值更新缓存
        User user3 = userService.updateUser(id, user1);
        System.out.println("user3 = " + user3);

        User user4 = userService.getOne(id);
        System.out.println("user4 = " + user4);
        User user5 = userService.getOne(id);
        // 引用一致
        Assert.assertSame(user4, user5);
    }

    @Test
    public void deleteTest()
    {
        String id = "2020";
        User user1 = userService.getOne(id);
        userService.delete(id);
        User user2 = userService.getOne(id);
        // 引用不一致
        Assert.assertNotSame(user1, user2);
    }

    @Test
    public void findOneTest()
    {
        String id = "2020";
        User user1 = userService.findOne(id);
        User user2 = userService.findOne(id);
        // 引用一致
        Assert.assertSame(user1, user2);
    }

}