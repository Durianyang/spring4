package top.ywlog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.util.*;

/**
 * @Author: Durian
 * @Date: 2020/4/5 21:37
 * @Description: 使用实现BeanFactoryAware的方式在单例bean注入多例bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boss2 implements BeanFactoryAware
{
    private String name;

    private Car car = new Car();

    List<String> favorites = new ArrayList<>();

    Set<String> girlFriends = new HashSet<>();

    private Map<Integer, Car> cars = new HashMap<>();

    private Properties mails = new Properties();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        this.beanFactory = beanFactory;
    }

    public Car getCar()
    {
        return beanFactory.getBean("car7", Car.class);
    }
}
