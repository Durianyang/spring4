package top.ywlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import top.ywlog.dao.JobDao;
import top.ywlog.pojo.Jobs;

import java.util.List;

/**
 * @Author: Durian
 * @Date: 2020/4/15 18:56
 * @Description:
 */
@Service
public class JobService
{
    @Autowired
    private JobDao jobDao;
    /**  编程式事务 */
    @Autowired
    private TransactionTemplate transactionTemplate;


    public Jobs findOne()
    {
        return jobDao.findOne();
    }

    public List<Jobs> findAll()
    {
        return jobDao.findAll();
    }

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public void add(String jobId)
    {
        jobDao.add(jobId);
//        throw new IllegalArgumentException();
    }

    public void update()
    {
        jobDao.update();
    }

    public void delete()
    {
        transactionTemplate.setReadOnly(false);
        // 有返回值用TransactionCallback<T>
        transactionTemplate.execute(new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus)
            {
                jobDao.delete();
            }
        });
    }

    public int count()
    {
       return jobDao.count();
    }
}
