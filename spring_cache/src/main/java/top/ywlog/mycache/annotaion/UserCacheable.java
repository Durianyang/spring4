package top.ywlog.mycache.annotaion;


import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

/**
 * @Author: Durian
 * @Date: 2020/4/17 15:32
 * @Description:
 */
@Cacheable(cacheNames = "users", key = "'user_' + #id")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserCacheable
{

}
