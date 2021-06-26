package cn.zyfvir;

import java.util.List;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/26 14:13
 */
public interface SysUserMapper {

    List<SysUser> selectDynamic(SysUser sysUser);

    int insertDynamic(SysUser user);

    void insertBatch(List<SysUser> list);

    int updateUserDynamic(SysUser user);

    int deleteById(int id);


}
