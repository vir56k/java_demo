package com.zhangyf.nettydemo;

import com.zhangyf.nettylib.NtMessage;
import com.zhangyf.utils.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

/**
 *
 */
@Sharable
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = EchoClientHandler.class.getSimpleName();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        LogUtil.d(TAG, "channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LogUtil.d(TAG, "on channelActive");

        NtMessage m = NtMessage.creatCommonMessage("12");
        ctx.writeAndFlush(m);
        LogUtil.d(TAG, "# 发送消息: " + m.getMessageString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LogUtil.d(TAG, "on channelRead, 收到消息：" + msg);
        if (msg instanceof NtMessage) {
            NtMessage m = (NtMessage) msg;
            if (m.isCommonMessage()) {
                LogUtil.d(TAG, "received CommonMessage");
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}