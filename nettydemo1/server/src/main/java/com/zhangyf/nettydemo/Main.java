package com.zhangyf.nettydemo;

public class Main {

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage: " + EchoServer.class.getSimpleName() +
//                    " <port>"
//            );
//            return;
//        }
//        int port = Integer.parseInt(args[0]);
        int port = 9000;
        new EchoServer(port).start();
    }
}
