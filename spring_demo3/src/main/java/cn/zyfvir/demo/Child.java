package cn.zyfvir.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/7/4 16:02
 */
@Component
public class Child {
    Dessert dessert;

    // 想要
    @Autowired
    @Qualifier("lollipop") // @Qualifier 声明了一个 优先选择的 限定名。
    public void wantDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public void eating() {
        System.out.println(String.format("正在吃 %s ...", dessert.getName()));
    }

}
