package cn.zyfvir;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            CityMapper mapper = session.getMapper(CityMapper.class);
            PageHelper.startPage(1, 5);
            List<City> page1 = mapper.selectByPage1();
            printArray(page1);
            //用PageInfo对结果进行包装
            PageInfo page = new PageInfo(page1);
            printf("page = %s", page);

            printf("************************");
            PageHelper.startPage(1, 7);
            List<City> page2 = mapper.selectByPage2();
            printArray(page2);

        }
    }

    private static void printArray(List<City> list) {
        printf("# 总数= %s,", list.size());
        for (City item : list) {
            printf("[ %s ],", item);
        }
    }

    private static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
