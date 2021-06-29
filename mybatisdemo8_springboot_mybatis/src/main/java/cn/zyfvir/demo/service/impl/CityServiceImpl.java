package cn.zyfvir.demo.service.impl;

import cn.zyfvir.demo.entity.City;
import cn.zyfvir.demo.mapper.CityMapper;
import cn.zyfvir.demo.service.CityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/29 23:33
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;

    @Override
    public City findByStatus(int status) {
        return cityMapper.findByStatus(status);
    }

    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }
}
