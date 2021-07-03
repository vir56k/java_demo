package cn.zyfvir.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/3 22:44
 */
@Configuration
public class HeroConfig {

    @Bean
    public Hero hero() {
        return new Hero(action());
    }

    @Bean
    public Action action() {
        return new SwordAction(System.out);
    }
}
