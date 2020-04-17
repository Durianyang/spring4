package top.ywlog.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Durian
 * @Date: 2020/4/10 23:35
 * @Description:
 * 分布式应用的配置属性多且重用时，可以将配置属性放入数据库，应用启动时读取
 * 模拟配置类，启动时从数据库读取配置文件用于其他类引用
 */
@Data
@Component
public class SysConfig
{
    private String name;
    private String password;
    private String url;

    @PostConstruct
    public void init()
    {
        // 模拟访问数据库
        this.name = "Durian";
        this.password = "19981016";
        this.url = "https://www.jd.com";
    }
}
