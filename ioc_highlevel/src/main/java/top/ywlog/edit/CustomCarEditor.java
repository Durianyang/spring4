package top.ywlog.edit;

import org.springframework.util.StringUtils;
import top.ywlog.bean.Car;

import java.beans.PropertyEditorSupport;

/**
 * @Author: Durian
 * @Date: 2020/4/10 22:50
 * @Description: 自定义属性编辑器类, 将该类以 map方式 注入到CustomEditorConfigurer
 * key为需要属性转换的bean， value为属性编辑器类
 *
 * CustomEditorConfigurer类实现了BeanFactoryPostProcessor，实际上是一个工厂后处理器
 */
public class CustomCarEditor extends PropertyEditorSupport
{
    private static final String SPLIT_STR = ",";

    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (StringUtils.isEmpty(text) || !text.contains(SPLIT_STR))
        {
            throw new IllegalArgumentException("属性设置格式错误！");
        }
        String[] infos = text.split(SPLIT_STR);
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        car.setMaxSpeed(Integer.parseInt(infos[2]));
        // 调用父类的setValue方法设置转换后的属性值
        setValue(car);
    }
}
