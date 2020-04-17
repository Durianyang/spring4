package top.ywlog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Durian
 * @Date: 2020/4/5 21:31
 * @Description: 测试用bean类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car
{
    private String brand;
    private String color;
    private int maxSpeed;
}
