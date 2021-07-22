package com.example.demo.controller;

import com.example.demo.entity.MyEntity;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String getTest(@RequestParam int id) {
        String str = testService.getEntityString(id);
        return str;
    }

    @GetMapping("/test2")
    public MyEntity getTest2(@RequestParam int id) {
        MyEntity entity = testService.getEntity(id);
        return entity;
    }

    @GetMapping("/test_has")
    public MyEntity getTestHash(@RequestParam int id) {
        MyEntity entity = testService.getTestHash(id);
        return entity;
    }

    @GetMapping("/get_by_email")
    public MyEntity getByEmail(@RequestParam String mail) {
        MyEntity entity = testService.getByEmail(mail);
        return entity;
    }
}
