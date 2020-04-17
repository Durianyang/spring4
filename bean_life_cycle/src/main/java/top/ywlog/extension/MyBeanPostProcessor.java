package top.ywlog.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.ywlog.bean.Car;

import java.util.Objects;

/**
 * @Author: Durian
 * @Date: 2020/4/4 22:22
 * @Description: 对bean进行处理，对配置文件提供的属性进行处理
 * Bean后处理器（重要），处理时间在bean的初始化前后
 * before方法在init(init-method和InitializingBean接口方法)前执行，
 * after方法在init（init-method和InitializingBean接口方法）后执行
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor
{
    private static final String BEAN_NAME = "car";
    private static final int MAX_SPEED = 200;

    /**
     * 将在InitializingBean的afterPropertiesSet()方法 和 init-method指定方法 之前 执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        if (Objects.equals(beanName, BEAN_NAME))
        {
            if (bean instanceof Car)
            {
                Car car = (Car) bean;
                if (car.getColor() != null)
                {
                    System.out.println("Car color 不为空，调用BeanPostProcessor的postProcessBeforeInitialization()设置为黑色");
                    car.setColor("黑色");
                }
            }
        }
        return bean;
    }

    /**
     * 将在InitializingBean的afterPropertiesSet()方法和 init-method指定方法 之后 执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        if (Objects.equals(beanName, BEAN_NAME))
        {
            if (bean instanceof Car)
            {
                Car car = (Car) bean;
                // 判断car的最大速度大于200，设置为200
                if (car.getMaxSpeed() > MAX_SPEED)
                {
                    System.out.println("Car maxSpeed 大于200，调用BeanPostProcessor的postProcessAfterInitialization()设置为200");
                    car.setMaxSpeed(200);
                }
            }
        }
        return bean;
    }

}
