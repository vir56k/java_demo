package cn.zyfvir.demo.mapper;

import cn.zyfvir.demo.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/29 23:01
 */
@Mapper
public interface CityMapper {

    @Select("SELECT * FROM city WHERE status = #{status}")
    City findByStatus(@Param("status") int status);

    List<City> findAll();
}