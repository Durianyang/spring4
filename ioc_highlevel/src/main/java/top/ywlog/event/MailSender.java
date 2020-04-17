package top.ywlog.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: Durian
 * @Date: 2020/4/11 22:21
 * @Description: 模拟邮件发送
 * MailSender要拥有发送事件的能力必须时间实现ApplicationContextAware接口
 */
@Component
public class MailSender implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }

    public void send(String to)
    {
        System.out.println("MailSender模拟邮件发送完成!");
        // 产生事件
        MailSendEvent event = new MailSendEvent(applicationContext, to);
        // 向容器中所有事件监听器发送事件(未自定义时间广播器，通过Spring默认注册的ApplicationEventMulticaster广播)
        applicationContext.publishEvent(event);
    }
}
