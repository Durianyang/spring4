package top.ywlog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;

import java.util.*;

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

    List<String> favorites = new ArrayList<>();

    Set<String> girlFriends = new HashSet<>();

    private Map<Integer, Car> cars = new HashMap<>();

    private Properties mails = new Properties();

    @Lookup
    public Car getPrototypeBean()
    {
        return null;
    }
}
