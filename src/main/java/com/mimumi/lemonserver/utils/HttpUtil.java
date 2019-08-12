package com.mimumi.lemonserver.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
public class HttpUtil {

    /**
     * 判断字符串是否为URL
     * @param urls 用户头像key
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-.=?~\\\\/])+$";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }

    public static boolean  isConnect(String urls) {
        boolean result=false;
        int counts = 0;
        if (urls == null || urls.length() <= 0) {
            return false;
        }
        while (counts < 3) {
            try {
                URL url = new URL(urls);
                HttpURLConnection  con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(3000);
                con.setReadTimeout(3000);
                con.connect();
                int state = con.getResponseCode();
                if (state == 200 || state==301|| state==302) {
                    result=true;
                    break;
                }
            }catch (Exception ex) {

            }finally {
                counts++;
            }
        }
        return result;
    }
}