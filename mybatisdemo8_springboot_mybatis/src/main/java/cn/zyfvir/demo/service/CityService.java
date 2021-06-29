package cn.zyfvir.demo.service;

import cn.zyfvir.demo.entity.City;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/29 23:42
 */
public interface CityService {
    City findByStatus(int status);

    List<City> findAll();
}
