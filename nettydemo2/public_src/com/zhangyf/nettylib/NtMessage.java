package com.zhangyf.nettylib;

import com.zhangyf.utils.LogUtil;
import com.zhangyf.utils.PrintUitl;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

public class NtMessage {
    private static final String TAG = NtMessage.class.getSimpleName();

    byte[] content;
    Header header;

    NtMessage() {
        header = new Header();
        this.content = new byte[0];
    }

    /**
     * 创建一个心跳消息
     *
     * @return
     */
    public static NtMessage creatPingMessage() {
        NtMessage n = new NtMessage();
        n.getHeader().setMessageType(Header.MESSAGE_TYPE_HEART_BEAT);
        return n;
    }

    /**
     * 创建一个心跳消息 的回复包
     *
     * @return
     */
    public static NtMessage creatPongMessage() {
        NtMessage n = new NtMessage();
        n.getHeader().setMessageType(Header.MESSAGE_TYPE_HEART_BEAT_REPLY);
        return n;
    }

    /**
     * 创建一个普通消息包
     *
     * @return
     */
    public static NtMessage creatCommonMessage() {
        NtMessage n = new NtMessage();
        n.getHeader().setMessageType(Header.MESSAGE_TYPE_COMMON);
        return n;
    }

    public static NtMessage creatCommonMessage(String str) {
        NtMessage n = new NtMessage();
        n.getHeader().setMessageType(Header.MESSAGE_TYPE_COMMON);
        n.content = (str == null || "".equals(str)) ? new byte[0] : str.getBytes();
        return n;
    }


    public Header getHeader() {
        return header;
    }

    public boolean isHeartbeat() {
        return header.isHeartbeat();
    }

    public boolean isCommonMessage() {
        return header.isCommonMessage();
    }

    public boolean isHeartbeatReplyMessage() {
        return header.isHeartbeatReplyMessage();
    }

    public String getMessageString() {
        return new String(content, CharsetUtil.UTF_8);
    }

    public byte getMessageType() {
        return getHeader().getMessageType();
    }


    @Override
    public String toString() {
        return "NtMessage{" +
                "type=[" + PrintUitl.bytesToHexString(header.getMessageType()) +
                ", header=[" + PrintUitl.bytesToHexString(header.bytes) +
                "], content=[" + PrintUitl.bytesToHexString(content) +
                "], 字符=[" + new String(content) +
                "]}";
    }

    /**
     * 消息类型(message type)说明：
     * type = 0, 心跳消息。
     * type = 1, 普通消息
     */
    public static class Header {
        public static final int HEADER_LENGTH = 6;
        public static final byte MESSAGE_TYPE_HEART_BEAT = 0x00;
        public static final byte MESSAGE_TYPE_HEART_BEAT_REPLY = 0x01;
        public static final byte MESSAGE_TYPE_COMMON = 0x02;

        byte[] bytes;

        public Header() {
            bytes = new byte[6];
        }

        public byte getMessageType() {
            return bytes[0];
        }

        public void setMessageType(byte messageType) {
            bytes[0] = messageType;
        }

        public boolean isHeartbeat() {
            return getMessageType() == MESSAGE_TYPE_HEART_BEAT;
        }

        public boolean isCommonMessage() {
            return getMessageType() == MESSAGE_TYPE_COMMON;
        }

        public boolean isHeartbeatReplyMessage() {
            return getMessageType() == MESSAGE_TYPE_HEART_BEAT_REPLY;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "bytes=" + PrintUitl.bytesToHexString(bytes) +
                    '}';
        }
    }

}
