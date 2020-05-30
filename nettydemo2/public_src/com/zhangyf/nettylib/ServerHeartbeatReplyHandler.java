package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 心跳。收到ping 回复 pong
 */
@Sharable
public class ServerHeartbeatReplyHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = ServerHeartbeatReplyHandler.class.getSimpleName();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LogUtil.d(TAG, "# on channelRead");
        if (msg instanceof NtMessage) {
            NtMessage m = (NtMessage) msg;
//            LogUtil.d(TAG, "# 消息是：" + m);
            if (m.isHeartbeat()) {
                onHandlerHeartbeat(ctx, m);
                return;
            }
        }
        try {
            super.channelRead(ctx, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理心跳消息
     *
     * @param ctx
     * @param m
     */
    private void onHandlerHeartbeat(ChannelHandlerContext ctx, NtMessage m) {
        LogUtil.d(TAG, "# 收到心跳：ping ...");
        ctx.writeAndFlush(NtMessage.creatPongMessage()).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    LogUtil.d(TAG, "# 回复心跳：pong.");
                }
            }
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
//        LogUtil.d(TAG, "on channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
