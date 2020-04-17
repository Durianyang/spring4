package top.ywlog.bean;

import lombok.Data;

/**
 * @Author: Durian
 * @Date: 2020/4/10 23:07
 * @Description: 通过xml配置文件注入外部文件中的属性
 */
@Data
public class MyProperty
{
    private String name;
    private String password;
    private String url;
}
