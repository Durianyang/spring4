package top.ywlog.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Durian
 * @Date: 2020/4/19 14:21
 * @Description:
 */
@RestControllerAdvice
public class WebExceptionHandler
{
    @ExceptionHandler(RuntimeException.class)
    public String error()
    {
        return "error";
    }
}
