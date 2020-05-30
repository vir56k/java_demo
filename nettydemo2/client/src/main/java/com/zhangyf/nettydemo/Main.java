package com.zhangyf.nettydemo;

import com.zhangyf.nettylib.EchoClient;

public class Main {


    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            System.err.println("Usage: " + EchoClient.class.getSimpleName() +
//                    " <host> <port>"
//            );
//            return;
//        }
//
//        final String host = args[0];
//        final int port = Integer.parseInt(args[1]);
        final String host = "localhost";
        int port = 9000;

        EchoClientHandler echoClientHandler = new EchoClientHandler();
        new EchoClient(host, port, echoClientHandler)
                .start();
    }
}
