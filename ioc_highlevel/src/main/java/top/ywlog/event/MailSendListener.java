package top.ywlog.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Durian
 * @Date: 2020/4/11 22:28
 * @Description: 邮件发送事件监听器
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent>
{
    @Override
    public void onApplicationEvent(MailSendEvent mailSendEvent)
    {
        // 具体处理逻辑
        System.out.println("向" + mailSendEvent.getTo() + "发送邮件完成！");
    }
}
