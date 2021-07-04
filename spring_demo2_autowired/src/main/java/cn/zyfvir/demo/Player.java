package cn.zyfvir.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 播放器
 * @author: zhangyunfei
 * @date: 2021/7/3 22:16
 */
@Component
public class Player {
    private CompactDisc compactDisc;

    // 自动装配
    @Autowired
    void insertCompactDisc(CompactDisc action) {
        this.compactDisc = action;
    }

    /**
     * 开始播放
     */
    void startPlay() {
        compactDisc.play();
    }

}
