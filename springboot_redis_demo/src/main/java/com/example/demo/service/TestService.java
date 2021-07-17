package com.example.demo.service;

import com.example.demo.dao.TestDao;
import com.example.demo.entity.MyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 *
 */
@Service
public class TestService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    TestDao testDao;

    public String getEntityString(int id) {
        System.out.println("# TestService::getEntityString " + id);
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        final String KEY = String.format("users:%s", id);
        String result = valueOperations.get(KEY);
        if (result == null || "".equals(result)) {
            result = testDao.getEntityStringById(id);
            valueOperations.set(KEY, result, Duration.ofSeconds(300));
        } else {
            System.out.println("# 缓存击中 " + id);
        }
        return result;
    }

    public MyEntity getEntity(int id) {
        System.out.println("# TestService::getEntity " + id);
        ValueOperations<String, MyEntity> valueOperations = redisTemplate.opsForValue();
        final String KEY = String.format("users:%s", id);
        MyEntity result = valueOperations.get(KEY);
        if (result == null || "".equals(result)) {
            result = testDao.getEntityById(id);
            valueOperations.set(KEY, result, Duration.ofSeconds(30));
        } else {
            System.out.println("# 缓存击中 " + id);
        }
        return result;
    }

    /**
     * 查询对象，同时 支持 按 email 读取
     *
     * @param id
     * @return
     */
    public MyEntity getTestHash(int id) {
        System.out.println("# TestService::getTestHash " + id);
        ValueOperations<String, MyEntity> valueOperations = redisTemplate.opsForValue();
        final String KEY = String.format("users:%s", id);
        MyEntity result = valueOperations.get(KEY);
        if (result == null || "".equals(result)) {
            result = testDao.getEntityById(id);

            // 按 id 存一份
            valueOperations.set(KEY, result, Duration.ofSeconds(300));
            String email = result.getEmail();
            // 同时按 email 存一个id 的引用
            final String KEY_MAIL = "users:by:email";
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            hashOperations.put(KEY_MAIL, email, result.getId() + "");
        } else {
            System.out.println("# 缓存击中 " + id);
        }
        return result;
    }

    // 按 mail 从缓存读
    public MyEntity getByEmail(String email) {
        System.out.println("# TestService::getByEmail " + email);
        final String KEY_MAIL = "users:by:email";
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String id = hashOperations.get(KEY_MAIL, email);
        if (id != null) {
            return getEntity(Integer.parseInt(id));
        }
        return null;
    }
}
