package top.ywlog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Durian
 * @Date: 2020/4/5 21:37
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boss
{
    private String name;
    private Car car = new Car();
}
