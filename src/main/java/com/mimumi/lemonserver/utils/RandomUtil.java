package com.mimumi.lemonserver.utils;


import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {

     static  char[] numchars	  = { '1','2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'};

    static  char[] chars	  = { '1','2', '3', '4', '5', '6', '7', '8', '9','0'};

    public static String genRandomNum(int len)
    {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < len)
        {
            i = Math.abs(r.nextInt(numchars.length));
            if (i >= 0 && i < numchars.length)
            {
                pwd.append(numchars[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    public static String genPhoneRandomNum(int len)
    {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < len)
        {
            i = Math.abs(r.nextInt(chars.length));
            if (i >= 0 && i < chars.length)
            {
                pwd.append(chars[i]);
                count++;
            }
        }
        return pwd.toString();
    }


}
