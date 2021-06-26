package cn.zyfvir;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        testInsertBatch(sqlSessionFactory);
        //testUpdate(sqlSessionFactory);
        //testSelect(sqlSessionFactory);
    }

    private static void testInsertBatch(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            List<SysUser> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                SysUser sysUser = new SysUser();
                sysUser.userName = "user" + i;
                sysUser.userPassword = "123";
                list.add(sysUser);
            }
            sysUserMapper.insertBatch(list);
        }
    }

    private static void testSelect(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            // 仅一个参数
            SysUser para = new SysUser();
            para.userName = "jack";
            List<SysUser> list1 = sysUserMapper.selectDynamic(para);
            printf("list=%s", list1.size());

            //两个参数都有
            SysUser para2 = new SysUser();
            para2.userName = "jack";
            para2.userEmail = "jack";
            List<SysUser> list2 = sysUserMapper.selectDynamic(para2);
            printf("list=%s", list2.size());

            // 只有 email
            SysUser para3 = new SysUser();
            para3.userEmail = "jack";
            List<SysUser> list3 = sysUserMapper.selectDynamic(para3);
            printf("list=%s", list3.size());
        }
    }

    private static void testUpdate(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            {
                SysUser user = new SysUser();
                user.id = 1;
                user.userPassword = "33";
                sysUserMapper.updateUserDynamic(user);
            }
            {
                SysUser user = new SysUser();
                user.id = 1;
                user.userName = "kkk";
                user.userPassword = "kk33";
                sysUserMapper.updateUserDynamic(user);
            }
        }
    }

    private static void testInsert(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // Insert
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            {
                SysUser user = new SysUser();
                user.userName = "jack22";
                user.userPassword = "123";
                int effctrows = sysUserMapper.insertDynamic(user);
                printf("简单插入：effctrows=%s, msg=%s", effctrows, user.toString());
            }
            {
                SysUser user = new SysUser();
                user.userName = "join";
                user.userPassword = "123";
                user.userEmail = "jooin@xxxxxx.com";
                int effctrows = sysUserMapper.insertDynamic(user);
                printf("插入后获得主键：effctrows=%s, msg=%s", effctrows, user.toString());
            }
        }
    }

    private static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
