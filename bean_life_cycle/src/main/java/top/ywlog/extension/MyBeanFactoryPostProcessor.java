package top.ywlog.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Durian
 * @Date: 2020/4/4 23:34
 * @Description: ApplicationContext环境下使用，
 * 在应用装载配置文件后，初始化bean实例之前将调用该BeanFactoryPostProcessor的方法对配置信息进行加工处理
 * 工厂后处理器是容器级别的，仅在应用上下文初始化时调用一次，目的是完成一些配置文件的加工处理。
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException
    {
        // 获取Car的bean定义信息
        BeanDefinition bf = configurableListableBeanFactory.getBeanDefinition("car");
        bf.getPropertyValues().addPropertyValue("brand", "比亚迪");
        System.out.println("调用BeanFactoryPostProcessor的postProcessBeanFactory()方法");
    }
}
