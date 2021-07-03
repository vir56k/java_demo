package cn.zyfvir.demo;

/**
 * @description: 英雄
 * @author: zhangyunfei
 * @date: 2021/7/3 22:16
 */
public class Hero {
    Action action;

    Hero(Action action) {
        this.action = action;
    }

    void play() {
        action.run();
    }

}
