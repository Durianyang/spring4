package top.ywlog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Jobs)实体类
 *
 * @author makejava
 * @since 2020-04-14 11:44:32
 */
@Data
public class Jobs implements Serializable
{
    private static final long serialVersionUID = 958386066272186260L;

    private String jobId;

    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;

    private LocalDateTime publicTime;

}