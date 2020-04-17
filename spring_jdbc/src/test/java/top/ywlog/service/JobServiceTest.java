package top.ywlog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Durian
 * @date 2020-04-16 22:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class JobServiceTest
{
    @Autowired
    private JobService jobService;

    @Test
    public void findOne()
    {
        System.out.println(jobService.findOne());
    }

    @Test
    public void findAll()
    {
        System.out.println(jobService.findAll());
    }

    @Test
    public void addTest()
    {
        jobService.add("ADD");
    }

    @Test
    public void updateTest()
    {
        jobService.update();
    }

    @Test
    public void deleteTest()
    {
        jobService.delete();
    }
}