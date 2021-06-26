package cn.zyfvir;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:13
 */
public interface SysUserMapper {

    int insert(SysUser user);

    int insert2(SysUser user);

    int updateUser(SysUser user);

    int deleteById(int id);

    List<SysUser> selectAll();

    SysUser selectByPrimaryKey(int id);
}
