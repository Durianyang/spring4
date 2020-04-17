package top.ywlog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import top.ywlog.service.UserService;

/**
 * @Author: Durian
 * @Date: 2020/4/15 0:26
 * @Description:
 */
@Aspect
@Component
public class UserAspectAnnotation
{
    @Pointcut("execution(* top.ywlog.service.*.delete*())||" +
              "execution(* top.ywlog.service.*.edit*())")
    public void userService(){}

    @Before("userService()")
    public void beforeMethod()
    {
        System.out.println("开启事务");
    }

    @AfterReturning(value = "userService()", returning = "rtValue")
    public void afterReturnMethod(boolean rtValue)
    {
        System.out.println("rtValue=" + rtValue);
        System.out.println("提交事务");
    }

    @After("userService()")
    public void afterMethod()
    {
        System.out.println("finally 释放资源");
    }

    @AfterThrowing(value = "userService()", throwing = "e")
    public void afterThrowingMethod(Exception e)
    {
        System.out.println("抛出异常类型：" + e.getClass());
        System.out.println("异常回滚事务");
    }

    @Around(value = "execution(* top.ywlog.service.*.insert*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjp)
    {
        Object rtValue = null;
        try
        {
            System.out.println("开始事务");
            rtValue = pjp.proceed();
            System.out.println("提交事务");
        } catch (Throwable throwable)
        {
            System.out.println("回滚");
        } finally
        {
            System.out.println("释放资源");
        }
        return rtValue;
    }
}
