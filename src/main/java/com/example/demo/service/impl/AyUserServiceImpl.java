package com.example.demo.service.impl;

import com.example.demo.dao.AyUserDao;
import com.example.demo.error.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.repository.AyUserRepository;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author wangX
 * @Decription 用戶服務層實現類
 * @date 2018/7/16 20:41
 */
@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {
   /* @Reference(version = "1.0.0")
    private AyUserDubboService ayUserDubboService;*/
    @Resource
    private AyUserRepository ayUserRepository;
    @Resource
    private AyUserDao ayUserDao;
    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";
    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public AyUser findById(String id) {
        //step.1 查询Redis 缓存中的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (ayUserList != null && ayUserList.size() > 0) {
            for (AyUser user : ayUserList) {
                if (user.getId().equals(id)) {
                    return user;
                }

            }
        }
        //step.2 查询数据库中的数据
        AyUser ayUser = ayUserRepository.findById(id).get();
        if (ayUser != null) {
            //step3.将数据插入到Redis缓存中去
            redisTemplate.opsForList().leftPush(ALL_USER, ayUser);
        }
        return ayUser;
    }

    @Override
    public List<AyUser> findAll() {
        try {
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return ayUserList;
        } catch (Exception e) {
            logger.error("method findAll error", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        try {
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return new AsyncResult<List<AyUser>>(ayUserList);
        } catch (Exception e) {
            logger.error("method findAll error", e);
            return new AsyncResult<List<AyUser>>(null);
        }
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }

    //注解在方法上
    @Transactional
    @Override
    public AyUser save(AyUser user) {
        AyUser saveUser = ayUserRepository.save(user);
        //出现空指针异常
        //String error =null;
        //error.split("/");
        return saveUser;
    }

    @Override
    public void delte(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId:" + id + "用户被删除");
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name, password);
    }

    @Override
    @Retryable(value={BusinessException.class},maxAttempts = 5,backoff=@Backoff(delay = 5000,multiplier = 2))
    public AyUser findByNameAndPassworRetry(String name, String password) {
        System.out.println("[findByNameAndPassworRestry] 方法失败重试了！ ");
        throw new BusinessException();
    }

    @Override
    public AyUser findByUserName(String name) {
        return ayUserDao.findByUserName(name);
    }

}
