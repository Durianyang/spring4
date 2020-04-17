package top.ywlog.mycache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Durian
 * @Date: 2020/4/17 12:44
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    private String id;
    private String name;
    private int age;
}
