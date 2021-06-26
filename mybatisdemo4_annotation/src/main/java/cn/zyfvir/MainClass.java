package cn.zyfvir;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/6/25 21:40
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.println("ready...");

        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //testInsert(sqlSessionFactory);
        testUpdate(sqlSessionFactory);
        //testDelete(sqlSessionFactory);
        //testSelect(sqlSessionFactory);
    }

    private static void testSelect(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            List<SysUser> list = sysUserMapper.selectAll();
            printf("list=%s", list.size());
            for (SysUser item: list) {
                printf("user=%s", item);
            }
            SysUser user = sysUserMapper.selectByPrimaryKey(2);
            printf("user=%s", user);
        }
    }

    private static void testDelete(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            sysUserMapper.deleteById(1);
        }
    }

    private static void testUpdate(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.id = 1;
            user.userPassword = "33";
            sysUserMapper.updateUser(user);
        }
    }

    private static void testInsert(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            // Insert
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.userName = "jack22";
            user.userPassword = "123";
            int effctrows = 0;
            effctrows = sysUserMapper.insert(user);
            printf("简单插入：effctrows=%s, msg=%s", effctrows, user.toString());

            effctrows = sysUserMapper.insert2(user);
            printf("插入后获得主键：effctrows=%s, msg=%s", effctrows, user.toString());
        }
    }

    private static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
