package cn.zyfvir;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/29 23:01
 */
public interface CityMapper {

    @Select("SELECT * FROM city ")
    List<City> selectByPage1();

    List<City> selectByPage2();

}