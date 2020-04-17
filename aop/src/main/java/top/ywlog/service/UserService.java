package top.ywlog.service;

import org.springframework.stereotype.Service;

/**
 * @Author: Durian
 * @Date: 2020/4/14 23:40
 * @Description:
 */
@Service
public class UserService
{
    public boolean
    addUser()
    {
        System.out.println("模拟向数据库添加记录......");
        int a = 1 / 0;
        return true;
    }

    public boolean updateUser()
    {
        System.out.println("模拟向数据库修改记录......");
        return true;
    }

    public boolean editUser()
    {
        System.out.println("模拟向数据库修改记录......");
        return true;
    }

    public boolean deleteUser()
    {
        System.out.println("模拟向数据库删除记录......");
        return true;
    }

    public boolean insertUser()
    {
        System.out.println("模拟向数据库添加记录......");
        return true;
    }

}
