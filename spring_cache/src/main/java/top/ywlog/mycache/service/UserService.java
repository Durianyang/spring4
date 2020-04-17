package top.ywlog.mycache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.ywlog.mycache.annotaion.UserCacheable;
import top.ywlog.mycache.entity.User;
import top.ywlog.mycache.manager.MyCacheManager;

import javax.annotation.PostConstruct;

/**
 * @Author: Durian
 * @Date: 2020/4/17 12:49
 * @Description: 模拟业务类
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService
{
    private final MyCacheManager<User> myCacheManager;
    private final CacheManager cacheManager;

    @Autowired
    public UserService(MyCacheManager<User> myCacheManager, CacheManager cacheManager)
    {
        this.myCacheManager = myCacheManager;
        this.cacheManager = cacheManager;
    }

    /**
     * 初始化缓存
     */
    @PostConstruct
    public void initCache()
    {
        System.out.println("@PostConstruct方法中查询数据库初始化缓存!");
        User user = getUser("2020");
        cacheManager.getCache("users").put("user_" + user.getId(), user);
    }

    /**
     * 使用自定义缓存类管理缓存
     *
     * @param id userId
     * @return userObj
     */
    public User findById(String id)
    {
        // 首先查询缓存
        User user = myCacheManager.getValue(id);
        if (user != null)
        {
            System.out.println("从缓存中获取数据...");
            System.out.println(user);
            return user;
        }
        user = getUser(id);
        if (user != null)
        {
            // 放入缓存
            myCacheManager.add(id, user);
        }
        System.out.println(user);
        return user;
    }

    @Cacheable(key = "'user_' + #id",
            condition = "#id.length() <= 4", unless = "#id.length() > 4")
    public User getOne(String id)
    {
        User user = getUser(id);
        System.out.println(user);
        return user;
    }

    @CachePut(key = "'user_' + #id",
            condition = "#root.method.name.contains('update') && #result != null")
    public User updateUser(String id, User user)
    {
        // 模拟修改数据库数据
        user = getUser(id);
        user.setName("Durian");
        user.setAge(22);
        return user;
    }

    @CacheEvict(key = "'user_' + #id", condition = "#id.length() <= 4",
            beforeInvocation = false)
    public void delete(String id)
    {
        remove(id);
    }

    @UserCacheable
    public User findOne(String id)
    {
        User user = getUser(id);
        System.out.println(user);
        return user;
    }

    public void removeCache(String key)
    {
        myCacheManager.evictCache(key);
        System.out.println("key = " + key + "的缓存已经清除！");
    }

    public void clearAll()
    {
        myCacheManager.clear();
        System.out.println("所有缓存已被清除！");
    }

    /**
     * 模拟操作数据库删除记录
     *
     * @param id userId
     */
    private void remove(String id)
    {
        System.out.println("Id为" + id + "的记录已删除");
    }

    /**
     * 模拟查询数据库
     *
     * @param id 用户id
     * @return 查询结果
     */
    private User getUser(String id)
    {
        // 模拟数据库查询
        System.out.println("从数据库中查询数据...");
        return new User(id, "Jack", 20);
    }
}
