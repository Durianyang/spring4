package top.ywlog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import top.ywlog.pojo.Jobs;
import top.ywlog.util.DateConvertUtil;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Durian
 * @Date: 2020/4/14 11:48
 * @Description:
 */
@Repository
public class JobDao
{
    private static final String SELECT_ONE_SQL = "select * from jobs where job_id = ?";
    private static final String SELECT_SQL = "select * from jobs";
    private static final String ADD_SQL = "insert into jobs(job_id, public_time) value(?, ?)";
    private static final String UPDATE_SQL = "update jobs set public_time = ? where job_id = ?";
    private static final String COUNT_SQL = "select count(*) from jobs";
    private static final String DELETE_SQL = "delete from jobs where job_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Jobs findOne()
    {
        Jobs jobs = new Jobs();
        jdbcTemplate.query(SELECT_ONE_SQL, new Object[]{"ST_MAN"}, resultSet ->
        {
            jobs.setJobId(resultSet.getString("job_id"));
            jobs.setJobTitle(resultSet.getString("job_title"));
            jobs.setMinSalary(resultSet.getInt("min_salary"));
            jobs.setMaxSalary(resultSet.getInt("max_salary"));
            Timestamp publicTime = resultSet.getTimestamp("public_time");
            jobs.setPublicTime(DateConvertUtil.timestampToLocalDateTime(publicTime));
        });
        return jobs;
    }

    public List<Jobs> findAll()
    {
        /*List<JobDao jobDao = context.getBean("jobDao", JobDao.class);Jobs> jobsList = new ArrayList<>(10);
        jdbcTemplate.query(SELECT_SQL, resultSet ->
        {
            Jobs jobs = new Jobs();
            jobs.setJobId(resultSet.getString("job_id"));
            jobs.setJobTitle(resultSet.getString("job_title"));
            jobs.setMinSalary(resultSet.getInt("min_salary"));
            jobs.setMaxSalary(resultSet.getInt("max_salary"));
            Timestamp publicTime = resultSet.getTimestamp("public_time");
            jobs.setPublicTime(DateConvertUtil.timestampToLocalDateTime(publicTime));
            jobsList.add(jobs);
        });
        return jobsList;*/

        return jdbcTemplate.query(SELECT_SQL, (resultSet, i) ->
        {
            Jobs jobs = new Jobs();
            jobs.setJobId(resultSet.getString("job_id"));
            jobs.setJobTitle(resultSet.getString("job_title"));
            jobs.setMinSalary(resultSet.getInt("min_salary"));
            jobs.setMaxSalary(resultSet.getInt("max_salary"));
            Timestamp publicTime = resultSet.getTimestamp("public_time");
            jobs.setPublicTime(DateConvertUtil.timestampToLocalDateTime(publicTime));
            return jobs;
        });
    }

    public void add(String jobId)
    {
        jdbcTemplate.update(ADD_SQL, ps ->
        {
            ps.setString(1, jobId);
            ps.setTimestamp(2, DateConvertUtil.localDateTimeToTimeStamp(LocalDateTime.now()));
        });
    }

    public void update()
    {
        // 尽量使用不带回调接口的方法
        Object[] params = new Object[]{DateConvertUtil.localDateTimeToTimeStamp(LocalDateTime.now()),
                "TEXT JOBs"};
        jdbcTemplate.update(UPDATE_SQL, params, new int[]{Types.TIMESTAMP, Types.VARCHAR});

        /*jdbcTemplate.update(UPDATE_SQL, preparedStatement ->
        {
            preparedStatement.setTimestamp(1, DateConvertUtil.localDateTimeToTimeStamp(LocalDateTime.now()));
            preparedStatement.setString(2, "TEXT JOBs");
        });*/
    }

    public void delete()
    {
        // 在事务方法中通过工具类获取的连接是事务共享的，事务结束后自动释放
        // 而在非事务方法中获取的连接是独立的，需要手动释放，否则会出现数据连接泄露
        Connection connection1 = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        Connection connection2 = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        System.out.println(connection1 == connection2);
        jdbcTemplate.update(DELETE_SQL, "ADD");
        throw new IllegalArgumentException();
    }

    public int count()
    {
        return jdbcTemplate.queryForObject(COUNT_SQL, Integer.class);
    }


}
