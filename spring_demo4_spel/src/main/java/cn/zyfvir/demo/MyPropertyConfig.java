package cn.zyfvir.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/5 00:14
 */
@Configuration
@PropertySource("classpath:myproperty_config.properties")
public class MyPropertyConfig {

    // 读取 配置文件中的 author.name
    @Value("${author.name}")
    public String authorName;

    @Value("#{3.1415}")
    public String pi;

    @Value("#{'xxxxx'}")
    public String string1;

    @Value("#{myPropertyConfig.getMyName().toUpperCase()}")
    public String myName;

    @Value("#{T(java.lang.Math).PI}")
    public String PI;

    @Value("#{T(java.lang.Math).random()}")
    public String random;

    @Value("#{ myPropertyConfig.pi == 3.14 }")
    public boolean is3_14;

    @Value("#{ myPropertyConfig.pi ?:'333' }")
    public String stirng2;

    public String getMyName() {
        return "zhang3";
    }

    @Value("#{ myPropertyConfig.getArray() }")
    public String[] array;


    @Value("#{ myPropertyConfig.array[1] }")
    public String array1;

    public String[] getArray() {
        String[] arr = new String[3];
        arr[0] = "#1";
        arr[1] = "#2";
        arr[2] = "#3";
        return arr;
    }

}
