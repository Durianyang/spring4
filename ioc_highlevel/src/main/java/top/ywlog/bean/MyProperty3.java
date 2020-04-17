package top.ywlog.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: Durian
 * @Date: 2020/4/10 23:19
 * @Description: 直接在类中引入外部文件属性
 */
@Data
//@Component
@PropertySource(value = {"classpath:properties/test.properties", "classpath:properties/url.properties"})
@Configuration
public class MyProperty3
{
    @Value("${name}")
    private String name;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;
}
