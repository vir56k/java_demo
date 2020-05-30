package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 用于捕获{@link IdleState#WRITER_IDLE}事件（未在指定时间内向服务器发送数据），
 * 然后向<code>Server</code>端发送一个心跳包。
 */
public class ClientHeartbeatHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "ClientHeartbeatHandler";

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        LogUtil.d(TAG, "userEventTriggered");

        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.ALL_IDLE) {
                // 遇到超时时发送一个心跳，并在发送失败时关闭该连接
                LogUtil.d(TAG, "send ping");
                ctx.writeAndFlush(NtMessage.creatPingMessage())
                        .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

}