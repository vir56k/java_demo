package cn.zyfvir.demo.controller;

import cn.zyfvir.demo.entity.City;
import cn.zyfvir.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/29 23:32
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping("/findByStatus")
    public City findByStatus(int status){
        return cityService.findByStatus(status);
    }

    @RequestMapping("/findAll")
    public List<City> findAll(){
        return cityService.findAll();
    }
}
