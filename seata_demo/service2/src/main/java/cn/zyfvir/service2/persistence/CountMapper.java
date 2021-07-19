package cn.zyfvir.service2.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface CountMapper {

    Count selectById(@Param("id") Integer id);

    int updateById(Count money);

}
