package top.ywlog.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import top.ywlog.bean.Car;

import java.beans.PropertyDescriptor;
import java.util.Objects;

/**
 * @Author: Durian
 * @Date: 2020/4/4 22:15
 * @Description: 全局的，也就是针对所有bean
 * 主要目的，围绕bean的实例化处理
 * 方法有实例化前，（实例化(如果是set方式注入属性，这里调用无参构造)）, 实例化后，设置属性
 */
@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
{
    private static final String BEAN_NAME = "car";

    /**
     * 在bean实例化前调用，此时bean还未被实例化
     * 对于每个bean（prototype作用域）来说，该方法只会在第一次创建bean时调用一次
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException
    {
        // 该方法可以为当前对象提供代理实现,例如SpringAOP，然后返回代理对象proxy
        if (Objects.equals(beanName, BEAN_NAME) || Objects.equals(beanName, "mobile"))
        {
            System.out.println("bean实例化前：InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation()");
        }
        return null;
    }

    /**
     * 在bean实例化后调用,此时bean已经通过无参构造器初始化
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException
    {
        if (Objects.equals(beanName, BEAN_NAME))
        {
            System.out.println("实例化后：" + bean);
            System.out.println("bean实例化后：InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation()");
        }
        return true;
    }

    /**
     * 在设置bean属性之前时调用
     *
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException
    {
        if (Objects.equals(beanName, BEAN_NAME))
        {
            System.out.println("设置属性前(set方式的话，属性为null,构造方式不一定)：" + bean);
            System.out.println("bean设置属性：InstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues()");
//            Car car = (Car) bean;
//            car.setColor("白色");
            System.out.println("属性设置前设置color，set方式的话会被覆盖");
        }
        return pvs;
    }
}
