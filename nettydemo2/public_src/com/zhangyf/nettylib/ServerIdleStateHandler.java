package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerIdleStateHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "ServerIdleStateHandler";
    private static final int IDLE_COUNT_MAX = 2;
    private int idle_count;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (IdleState.READER_IDLE.equals(event.state())) { // 如果读通道处于空闲状态，说明没有接收到心跳命令
                if (idle_count > IDLE_COUNT_MAX) {
                    LogUtil.d(TAG, "# 超过%s次无客户端请求，关闭该channel", IDLE_COUNT_MAX);
                    ctx.channel().close().addListener(
                            new ChannelFutureListener() {
                                @Override
                                public void operationComplete(ChannelFuture future) throws Exception {
                                    LogUtil.d(TAG, "# 超时，关闭连接！！！");
                                }
                            });

                    idle_count++;
                } else {
                    LogUtil.d(TAG, "# 已等待 %s 次还没收到客户端发来的消息", idle_count + 1);
                }
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

}
