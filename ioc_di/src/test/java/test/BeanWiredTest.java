package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ywlog.bean.Boss;
import top.ywlog.bean.Boss2;
import top.ywlog.bean.Car;

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
        // 属性注入
        Car car1 = context.getBean("car1", Car.class);
        System.out.println("属性注入 : " + car1);

        // 构造器注入
        Car car2 = context.getBean("car2", Car.class);
        System.out.println("构造器注入 : " + car2);

        // 工厂方法注入
        Car car4 = context.getBean("car4", Car.class);
        System.out.println("工厂方法注入 : " + car4);

        // 工厂静态方法注入
        Car car5 = context.getBean("car5", Car.class);
        System.out.println("工厂静态方法注入 : " + car5);

        // p命名空间简化配置
        Car car6 = context.getBean("car6", Car.class);
        System.out.println("p命名空间简化配置 : " + car6);
    }

    @Test
    public void propertyWiredTest()
    {
        // 使用内部bean注入属性
        Boss boss = context.getBean("boss3", Boss.class);
        System.out.println("内部bean注入属性： " + boss);

        // 级联操注入属性
        Boss boss4_1 = context.getBean("boss4", Boss.class);
        Boss boss4_2 = context.getBean("boss4", Boss.class);
        System.out.println("级联操注入属性： " + boss4_1);
        System.out.println("boss1.car == boss2.car = " + (boss4_1.getCar() == boss4_2.getCar()));

        // 注入list类型属性
        Boss boss5 = context.getBean("boss5", Boss.class);
        System.out.println("注入list类型属性 " + boss5);
        // 注入set类型属性
        Boss boss6 = context.getBean("boss6", Boss.class);
        System.out.println("注入set类型属性 " + boss6);
        // 注入map类型属性
        Boss boss7 = context.getBean("boss7", Boss.class);
        System.out.println("注入map类型属性 " + boss7);
        // 注入properties
        Boss boss8 = context.getBean("boss8", Boss.class);
        System.out.println("注入 properties 类型属性 " + boss8);
    }

    @Test
    public void ScopeTest()
    {
        // BeanFactoryAware接口实现
        Boss2 boss9_1 = context.getBean("boss9", Boss2.class);
        System.out.println(boss9_1);
        Boss2 boss9_2 = context.getBean("boss9", Boss2.class);
        System.out.println(boss9_2);
        System.out.println(boss9_1.getCar() == boss9_2.getCar());

        // lookup方法注入
        Boss boss1 = context.getBean("boss10", Boss.class);
        System.out.println(boss1);
        Boss boss2 = context.getBean("boss10", Boss.class);
        System.out.println(boss2);
        System.out.println(boss1.getCar() == boss2.getCar());
    }
}
