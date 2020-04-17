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
public class MyProperty2
{
    @Value("${name}")
    private String name;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;

}
