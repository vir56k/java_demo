package utils;

import entity.Worth;

import java.io.PrintStream;
import java.util.List;

/**
 *
 */
public class PrintUtil {
    static PrintStream printStream = System.out;

    public static void printf(List<?> list) {
        if(list == null) {
            printStream.println("NULL");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        for (Object obj : list) {
            sb.append(obj).append(",");
        }
        printStream.println(sb.toString());
    }

    public static void printf2(List<?> list) {
        if(list == null) {
            printStream.println("NULL");
            return;
        }
        for (Object obj : list) {
            printStream.println(obj);
        }
    }
}
