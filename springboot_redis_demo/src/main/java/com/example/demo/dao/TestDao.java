package com.example.demo.dao;

import com.example.demo.entity.MyEntity;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class TestDao {


    public String getEntityStringById(int id) {
        System.out.println("# 触发数据访问层 getEntityStringById ");
        return "hello," + id;
    }

    public MyEntity getEntityById(int id) {
        System.out.println("# 触发数据访问层 getEntityById ");
        MyEntity myEntity = new MyEntity();
        myEntity.setId(id);
        myEntity.setName("zhang_" + id);
        myEntity.setAge(18);
        myEntity.setEmail(id + "@163.com");
        return myEntity;
    }

}
