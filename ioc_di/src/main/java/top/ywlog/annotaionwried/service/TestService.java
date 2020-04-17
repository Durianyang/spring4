package top.ywlog.annotaionwried.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import top.ywlog.annotaionwried.dao.CategoryDao;
import top.ywlog.annotaionwried.dao.IUserDao;

/**
 * @Author: Durian
 * @Date: 2020/4/6 0:38
 * @Description:
 */
@Service
public class TestService
{
    @Autowired
    private IUserDao adminDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    @Qualifier("userDao")
    private IUserDao dao;

    public void test()
    {
        System.out.println(adminDao);
        System.out.println(categoryDao);
        System.out.println(userDao);
        System.out.println(dao);
    }
}
