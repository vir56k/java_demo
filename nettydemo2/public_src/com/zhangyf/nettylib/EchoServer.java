package com.zhangyf.nettylib;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class EchoServer {
    private final int port;
    private final ChannelInboundHandlerAdapter businessHandler;

    public EchoServer(int port, final ChannelInboundHandlerAdapter businessHandler) {
        this.port = port;
        this.businessHandler = businessHandler;
    }

    public void start() throws Exception {
//        LengthFieldBasedFrameDecoder
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
//                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

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
                                    .addLast("idleStateHandler", new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS))
                                    // 空闲处理器
                                    .addLast("serverIdleStateHandler", new ServerIdleStateHandler())
                                    // 处理收到心跳ping,回复pong ,并不再传递
                                    .addLast("serverHeartbeatReplyHandler", new ServerHeartbeatReplyHandler());

                            ch.pipeline()
                                    .addLast(businessHandler);
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
}