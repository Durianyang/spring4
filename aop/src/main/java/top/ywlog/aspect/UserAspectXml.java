package top.ywlog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Durian
 * @Date: 2020/4/14 23:42
 * @Description:
 */
@Component
public class UserAspectXml
{
    public void beforeAdvice()
    {
        System.out.println("开启事务");
    }

    public void afterReturnAdvice(boolean flag)
    {
        System.out.println("flag=" + flag);
        System.out.println("提交事务");
    }

    @Order(1)
    public void throwingAdvice(Exception e)
    {
        System.out.println("出现异常类型为:" + e.getClass());
        System.out.println("发生异常，回滚事务");
    }

    public void afterAdvice()
    {
        System.out.println("finally 释放资源");
    }

    @Order(2)
    public Object aroundAdvice(ProceedingJoinPoint pjp)
    {
        Object rtValue = null;
        try
        {
            System.out.println("开启事务2");
            rtValue = pjp.proceed();
            System.out.println("提交事务2");
        } catch (Throwable throwable)
        {
            throwable.printStackTrace();
            System.out.println("异常回滚事务2");
        } finally
        {
            System.out.println("finally 释放资源2");
        }
        return rtValue;
    }
}
