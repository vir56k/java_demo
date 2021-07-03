package cn.zyfvir.demo;

import java.io.PrintStream;

/**
 * @description: 动作：使用 剑
 * @author: zhangyunfei
 * @date: 2021/7/3 22:25
 */
public class SwordAction implements Action {
    private PrintStream printStream;

    SwordAction(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void run() {
        printStream.println(String.format("use %s attack.", "sword"));
    }

}
