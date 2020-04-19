package top.ywlog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2020-04-18 20:46:48
 */
@Data
public class SysUser implements Serializable
{
    private static final long serialVersionUID = -81732593425571062L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 简介
     */
    private String userInfo;
    /**
     * 头像
     */
    private byte[] headImg;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}