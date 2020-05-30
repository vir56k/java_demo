package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * NtMessage To Buf
 */
public class NtMessageToBufEncoder extends MessageToByteEncoder<NtMessage> {

    private static final String TAG = NtMessageToBufEncoder.class.getSimpleName();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        LogUtil.d(TAG, "handlerAdded");
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NtMessage msg, ByteBuf out) throws Exception {
//        LogUtil.d(TAG, "encode");
        if (msg == null) return;
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.header.bytes).writeBytes(msg.content);
        out.writeBytes(buf);
    }
}
