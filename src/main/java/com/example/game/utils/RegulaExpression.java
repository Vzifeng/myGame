package com.example.game.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 17:05 2019/6/14
 * @Version ： $version$
 */
public class RegulaExpression {
    //手机号码正则表达式
    public static final String PHONEREGULAR = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    public static boolean isPhoneNum(String phoneNum){
        Pattern pattern = Pattern.compile(PHONEREGULAR);
        Matcher matcher = pattern.matcher(phoneNum);
        boolean isMatch = matcher.matches();
        return isMatch;
    }

    public static void main(String[] args) {
        boolean b = isPhoneNum("13996797261");
        if (b){
            System.out.println("zhengque");
        }else {
            System.out.println("cuowu");
        }
    }
}
