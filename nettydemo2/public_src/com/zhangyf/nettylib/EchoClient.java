package com.zhangyf.nettylib;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 客户端程序
 * <p>
 * handler 功能说明如下：
 * 辅助开发的日志：logging
 * 消息头部的解析，粘包拆包处理：通过：frameDecoder 和 frameEncoder
 * 字节和消息的互转：decoder 和 encoder
 * 心跳定期发送：通过 ping_loop  实现
 * 心跳失败处理：通过 IdleStateHandler 和 HeartbeatHandler 实现
 * 最终的实际处理消息的类：businessHandler
 */
public class EchoClient {
    private final String host;
    private final int port;
    private final ChannelInboundHandlerAdapter businessHandler;

    public EchoClient(String host, int port, final ChannelInboundHandlerAdapter businessHandler) {
        this.host = host;
        this.port = port;
        this.businessHandler = businessHandler;
    }

    public void start()
            throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline()
                                    // 日志方式1
//                                    .addLast("logging", new LoggingHandler(LogLevel.INFO))
                                    // 处理消息开始长度字节，消息头字节。解决粘包拆包。
                                    .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(4096, 0, 2, 0, 2))
                                    .addLast("frameEncoder", new LengthFieldPrepender(2, 0))
                                    // 编码器和解码器
                                    .addLast("decoder", new BufToNtMessageDecoder())
                                    .addLast("encoder", new NtMessageToBufEncoder())
                                    // 空闲超时检测器（触发器）
                                    .addLast("idleStateHandler", new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS))
                                    // 空闲处理器：遇到超时时发送一个心跳，并在发送失败时关闭该连接
                                    .addLast("heartbeatHandler", new ClientHeartbeatHandler())
                                    // 循环发心跳
                                    .addLast("ping_loop", new ClientHeartbeatLoopHandler())
                                    //收到pong不传递
                                    .addLast("replyHandler", new ClientHeartbeatReplyHandler());
                            // 附加具体业务处理器
                            if (businessHandler != null)
                                ch.pipeline()
                                        .addLast(businessHandler);
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
