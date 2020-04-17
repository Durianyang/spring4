package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ywlog.bean.*;

/**
 * @Author: Durian
 * @Date: 2020/4/5 21:49
 * @Description: 测试Spring的bean注入方式
 */
public class BeanWiredTest
{
    private static ApplicationContext context;

    @Before
    public void init()
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test()
    {
        Boss boss = context.getBean("boss", Boss.class);
        System.out.println(boss);
    }

    @Test
    public void externalPropertyTest()
    {
        // xml
        MyProperty myProperty = context.getBean("myProperty", MyProperty.class);
        System.out.println(myProperty);

        // xml外部引入
        MyProperty2 myProperty2 = context.getBean("myProperty2", MyProperty2.class);
        System.out.println(myProperty2);

        // 注解直接引入
        MyProperty3 myProperty3 = context.getBean("myProperty3", MyProperty3.class);
        System.out.println(myProperty3);

        // xml
        MyProperty myPropertyBySysConfig = context.getBean("myPropertyBySysConfig", MyProperty.class);
        System.out.println(myPropertyBySysConfig);

        // 注解
        MyProperty4 myPropertyBySysConfig2 = context.getBean("myProperty4", MyProperty4.class);
        System.out.println(myPropertyBySysConfig2);

    }
}
