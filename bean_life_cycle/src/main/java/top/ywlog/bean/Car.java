package top.ywlog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author: Durian
 * @Date: 2020/4/4 21:53
 * @Description: 测试Bean在BeanFactory下生命周期用的bean
 */
@Data
@AllArgsConstructor
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware
{
    private String brand;
    private String color;
    private int maxSpeed;

    private BeanFactory beanFactory;
    private String beanName;

    public Car()
    {
        System.out.println("调用Car无参构造函数");
    }

    public Car(String brand, String color, int maxSpeed)
    {
        System.out.println("有参构造器调用");
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public Car(String brand, int maxSpeed)
    {
        System.out.println("有参构造器调用");
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        System.out.println("构造器调用完成");
    }

    public void setBrand(String brand)
    {
        System.out.println("set:" + this);
        System.out.println("bean 的 set 方法调用");
        this.brand = brand;
        System.out.println(this);
    }

    public void setColor(String color)
    {
        System.out.println("bean 的 set 方法调用");
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed)
    {
        System.out.println("bean 的 set 方法调用");
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        System.out.println("调用BeanFactoryAware的setBeanFactory()方法");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName)
    {
        System.out.println("调用BeanNameAware的setBeanName()方法");
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        System.out.println("调用InitializingBean的afterPropertiesSet()方法");
    }

    @Override
    public void destroy() throws Exception
    {
        System.out.println("调用DisposableBean的destroy()方法");
    }

    /**
     * init-method指定的方法，在InitializingBean的afterPropertiesSet()之后执行
     */
    @PostConstruct
    public void myInit()
    {
        System.out.println("调用init-method指定的myInit，将maxSpeed初始化为240");
        this.maxSpeed = 240;
    }

    /**
     * destroy-method指定的方法，在DisposableBean的destroy方法后执行
     */
    @PreDestroy
    public void myDestroy()
    {
        System.out.println("调用destroy-method指定的myDestroy方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        System.out.println("调用ApplicationContextAware的setApplicationContext方法");
    }
}
