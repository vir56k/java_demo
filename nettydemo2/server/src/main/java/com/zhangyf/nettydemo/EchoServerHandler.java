package com.zhangyf.nettydemo;

import com.zhangyf.nettylib.NtMessage;
import com.zhangyf.utils.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 *
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "EchoServerHandler";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LogUtil.d(TAG, "on channelRead");
        if (msg instanceof NtMessage) {
            NtMessage m = (NtMessage) msg;
            if (m.isCommonMessage()) {
                onHandleCommonMessage(ctx, m);
            }
        }
    }

    private void onHandleCommonMessage(ChannelHandlerContext ctx, NtMessage m) {
        LogUtil.d(TAG, "onHandleCommonMessage");
        String json = m.getMessageString();
        LogUtil.d(TAG, "收到：" + json);

        String newMsg = "hello," + json;
        LogUtil.d(TAG, "# 回复消息:"+ newMsg);
        ctx.writeAndFlush(NtMessage.creatCommonMessage(newMsg)).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                LogUtil.d(TAG, "# 回复消息 成功");
            }
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        LogUtil.d(TAG, "on channelReadComplete");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
