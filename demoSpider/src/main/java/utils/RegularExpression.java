package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegularExpression {

    public static String findByFirst(String input, String regular) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }
        return null;
    }

}
