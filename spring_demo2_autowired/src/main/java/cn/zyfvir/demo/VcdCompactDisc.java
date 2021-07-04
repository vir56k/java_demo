package cn.zyfvir.demo;


import org.springframework.stereotype.Component;

/**
 * @description: VCD 光盘
 * @author: zhangyunfei
 * @date: 2021/7/3 22:25
 */
@Component
public class VcdCompactDisc implements CompactDisc {
    private String title = "经典歌曲-忘情水";

    public void play() {
        System.out.println(String.format(" %s  正在播放...", title));
    }

}
