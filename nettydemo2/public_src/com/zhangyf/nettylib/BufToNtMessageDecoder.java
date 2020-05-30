package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解析头部的10个字节
 */
public class BufToNtMessageDecoder extends ByteToMessageDecoder {
    private static final String TAG = "BufToNtMessageDecoder";
    private static final boolean DEBUG = false;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LogUtil.d(TAG,"channelActive");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        print("handlerAdded");
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        print("on decode");
        if (byteBuf.readableBytes() < NtMessage.Header.HEADER_LENGTH) {
            ctx.fireExceptionCaught(new Exception(String.format("消息的长度至少要大于约定的%s个字节的头部长度", NtMessage.Header.HEADER_LENGTH)));
        } else {
            print("可读字节数：readableBytes() = " + byteBuf.readableBytes());
            NtMessage ntMessage = new NtMessage();
            print("(1) msg = %s", ntMessage);
            byteBuf.readBytes(ntMessage.header.bytes);//个字节
            print("(2) msg = %s", ntMessage);
            if (byteBuf.readableBytes() > 0) {
                ntMessage.content = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(ntMessage.content);
                print("(3) msg = %s", ntMessage);
            } else {
                print("(3) 空消息体");
            }
            out.add(ntMessage);
        }
    }

    private void print(String format, Object... para) {
        if (!DEBUG) return;
        LogUtil.d(TAG, format, para);
    }
}
