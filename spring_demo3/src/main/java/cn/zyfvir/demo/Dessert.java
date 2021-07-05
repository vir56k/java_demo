package cn.zyfvir.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 甜点
interface Dessert {
    String getName();
}

@Component
class IceCream implements Dessert{
    private String name = "冰淇淋";

    public String getName() {
        return name;
    }

}

// 在这里 @Primary 可用于声明 要被优先使用
//@Primary
@Component
class Chocolate implements Dessert{
    private String name = "巧克力";

    public String getName() {
        return name;
    }
}

@Component
class Lollipop implements Dessert{
    private String name = "棒棒糖";

    public String getName() {
        return name;
    }
}


