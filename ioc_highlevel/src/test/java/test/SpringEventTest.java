package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ywlog.event.MailSender;

/**
 * @Author: Durian
 * @Date: 2020/4/11 22:29
 * @Description: Spring模拟邮件发送事件
 */
public class SpringEventTest
{
    private static ApplicationContext context;

    @Before
    public void init()
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    @Test
    public void sendMailTest()
    {
        MailSender mailSender = context.getBean("mailSender", MailSender.class);
        mailSender.send("test@mail.com");
    }
}
