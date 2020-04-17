package top.ywlog.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ywlog.pojo.Jobs;

import java.util.List;

public class JobDaoTest
{
    private static ApplicationContext context;

    @Before
    public void init()
    {
        context = new ClassPathXmlApplicationContext("spring-dao.xml");
    }

    @Test
    public void findOne()
    {
        JobDao jobDao = context.getBean("jobDao", JobDao.class);
        Jobs one = jobDao.findOne();
        System.out.println("one = " + one);
    }

    @Test
    public void findAllTest()
    {
        JobDao jobDao = context.getBean("jobDao", JobDao.class);
        List<Jobs> all = jobDao.findAll();
        System.out.println(all);
    }

    @Test
    public void add()
    {
        JobDao jobDao = context.getBean("jobDao", JobDao.class);
        jobDao.add("OOO");
    }

    @Test
    public void updateUserTest()
    {
        JobDao jobDao = context.getBean("jobDao", JobDao.class);
        jobDao.update();
    }

    @Test
    public void countTest()
    {
        JobDao jobDao = context.getBean("jobDao", JobDao.class);
        System.out.println(jobDao.count());
    }
}