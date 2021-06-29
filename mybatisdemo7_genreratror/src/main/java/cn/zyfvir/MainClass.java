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

        testSelect(sqlSessionFactory);
    }

    private static void testSelect(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            AuthorMapper blogsMapper = session.getMapper(AuthorMapper.class);
            List<Author> list = blogsMapper.selectAuthorList();
            printf("list 长度=%s", list.size());
            for (Author item : list) {
                printf("%s", item);
            }
        }
    }

    private static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
