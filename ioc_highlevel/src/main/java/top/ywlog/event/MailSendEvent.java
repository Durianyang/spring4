package top.ywlog.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @Author: Durian
 * @Date: 2020/4/11 22:25
 * @Description: 邮件发送事件,需要继承Spring提供的事件类，用来代表一个事件
 */
class MailSendEvent extends ApplicationContextEvent
{
    private String to;

    MailSendEvent(ApplicationContext source, String to)
    {
        super(source);
        this.to = to;
    }

    String getTo()
    {
        return this.to;
    }
}
