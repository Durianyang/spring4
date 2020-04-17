package top.ywlog.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Durian
 * @Date: 2020/4/10 23:15
 * @Description: 通过注解引入外部文件属性
 */
@Data
@Component
public class MyProperty4
{
    @Value("#{sysConfig.name}")
    private String name;
    @Value("#{sysConfig.password}")
    private String password;
    @Value("#{sysConfig.url}")
    private String url;
}
