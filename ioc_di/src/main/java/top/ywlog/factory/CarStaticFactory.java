package top.ywlog.factory;

import top.ywlog.bean.Car;

/**
 * @Author: Durian
 * @Date: 2020/4/5 21:44
 * @Description:
 */
public class CarStaticFactory
{
    public static Car createCar()
    {
        Car car = new Car();
        car.setBrand("红旗");
        car.setColor("黑色");
        car.setMaxSpeed(200);
        return car;
    }
}
