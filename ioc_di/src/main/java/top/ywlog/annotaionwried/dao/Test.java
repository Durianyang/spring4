package top.ywlog.annotaionwried.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ywlog.annotaionwried.service.TestService;

/**
 * @Author: Durian
 * @Date: 2020/4/6 0:39
 * @Description:
 */
public class Test
{
    private static ApplicationContext context;

    @Before
    public void initTest()
    {
        context = new ClassPathXmlApplicationContext("Annotation.xml");
    }

    @org.junit.Test
    public void testServiceTest()
    {
        // 当@Autowired根据类型查找不到时，将按照变量或方法查找bean，如果没有，报错
        // 当名称查不到，而类型有多个时，报错，可使用@Qualify按名称指定注入bean
        TestService testService = context.getBean("testService", TestService.class);
        System.out.println(testService);
        testService.test();
    }
}
