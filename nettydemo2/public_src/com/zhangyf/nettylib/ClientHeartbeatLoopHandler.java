package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 周期性的 心跳处理
 */
public class ClientHeartbeatLoopHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = ClientHeartbeatLoopHandler.class.getSimpleName();

    private Random random = new Random();
    private int baseRandom = 20;

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.channel = ctx.channel();

        ping(ctx.channel());
    }

    private void ping(final Channel channel) {
        int second = 12 + Math.max(1, random.nextInt(8));
//        LogUtil.d(TAG, "next heart beat will send after " + second + "s.");
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {


            @Override
            public void run() {
                if (channel.isActive()) {
                    LogUtil.d(TAG, "## 发送一个心跳包 ping");
                    channel.writeAndFlush(NtMessage.creatPingMessage());
                } else {
                    LogUtil.d(TAG, "The connection had broken, cancel the task that will send a heart beat.");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }

        }, second, TimeUnit.SECONDS);

        future.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    ping(channel);
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当Channel已经断开的情况下, 仍然发送数据, 会抛异常, 该方法会被调用.
        cause.printStackTrace();
        ctx.close();
    }
}