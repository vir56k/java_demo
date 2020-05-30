package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 */
public class ClientHeartbeatReplyHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = ClientHeartbeatReplyHandler.class.getSimpleName();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        LogUtil.d(TAG, "channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LogUtil.d(TAG, "on channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NtMessage) {
            NtMessage m = (NtMessage) msg;
            if (m.isHeartbeat()) {
                LogUtil.d(TAG, "received ping ");
            } else if (m.isHeartbeatReplyMessage()) {
                LogUtil.d(TAG, "# 收到心跳 pong");
            } else {
                super.channelRead(ctx, msg);// 继续传递
            }
        } else {
            super.channelRead(ctx, msg);// 继续传递
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}