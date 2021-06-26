package cn.zyfvir;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:13
 */
public interface SysUserMapper {

    @Insert({"INSERT INTO sys_user(user_name,user_password,user_email,user_info) values(#{userName},#{userPassword},#{userEmail},#{userInfo})"})
    int insert(SysUser user);

    // 返回非自增主键
    @Insert({"INSERT INTO sys_user(user_name,user_password,user_email,user_info) values(#{userName},#{userPassword},#{userEmail},#{userInfo})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysUser user);

    @Update({"UPDATE sys_user SET user_name= #{userName}, user_password= #{userPassword}, user_email=#{userEmail}, user_info= #{userInfo} WHERE id=#{id}"})
    int updateUser(SysUser user);

    @Delete({"DELETE FROM sys_user WHERE id=#{id}"})
    int deleteById(int id);

    @Select({"select id, user_name, user_password, user_email, user_info from sys_user"})
    List<SysUser> selectAll();

    @Select({"select id, user_name, user_password, user_email, user_info from sys_user WHERE id=#{id}"})
    SysUser selectByPrimaryKey(int id);
}
