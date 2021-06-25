package cn.zyfvir;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


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

        try (SqlSession session = sqlSessionFactory.openSession()) {
            Country blog = (Country) session.selectOne("cn.zyfvir.CountryMapper.selectBlog", 1);
            printf(blog);
        }
    }

    private static void printf(Country blog) {
        System.out.println("" + blog.id + ", " + blog.countryName);
    }
}
