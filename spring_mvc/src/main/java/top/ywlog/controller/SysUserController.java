package top.ywlog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import top.ywlog.entity.SysUser;
import top.ywlog.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-04-18 20:49:38
 */
@Slf4j
@RestController
@RequestMapping("sysUser")
public class SysUserController
{
    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService)
    {
        this.sysUserService = sysUserService;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne/{id}")
    public SysUser selectOne(@PathVariable("id") Long id)
    {
        int i = 1/0;
        return this.sysUserService.queryById(id);
    }

    @PostMapping("/insert")
    public SysUser insert(SysUser sysUser, MultipartFile file)
    {
        if (!file.isEmpty())
        {
            try
            {
                byte[] bytes = file.getBytes();
                sysUser.setHeadImg(bytes);
            } catch (IOException e)
            {
                log.error("文件保存失败！");
            }
        }
        log.info("添加用户的表单数据: {}", sysUser);
        return sysUserService.insert(sysUser);
    }
}