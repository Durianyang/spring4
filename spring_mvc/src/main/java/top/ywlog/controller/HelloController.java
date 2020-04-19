package top.ywlog.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: Durian
 * @Date: 2020/4/18 20:17
 * @Description:
 */
@Controller
@RequestMapping("/hello")
public class HelloController
{
    @GetMapping("/hello")
    @ResponseBody
    public String hello()
    {
        return "hello!";
    }

    @GetMapping("/hi")
    public String hi()
    {
        return "hello";
    }

    @GetMapping("/{path}")
    public String jump(@PathVariable String path)
    {
        return path;
    }

    @GetMapping("/outStream")
    public void outImg(OutputStream out)
    {
        out(out);
    }

    private void out(OutputStream out)
    {
        Resource resource = new ClassPathResource("favicon.ico");
        try
        {
            FileCopyUtils.copy(resource.getInputStream(), out);
        } catch (IOException e)
        {
            throw new RuntimeException();
        }
    }
}
