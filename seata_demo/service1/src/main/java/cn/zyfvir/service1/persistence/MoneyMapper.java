package cn.zyfvir.service1.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 *
 */
@Mapper
public interface MoneyMapper {

    Money selectById(@Param("id") Integer id);

    int updateById(Money money);

}
