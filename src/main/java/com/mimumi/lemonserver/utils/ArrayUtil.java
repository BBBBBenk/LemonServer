package com.mimumi.lemonserver.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version V1.0
 * @desc 数组工具类
 */
public class ArrayUtil {

    public static int[] StringToInt(String[] arrs){
        int[] ints = new int[arrs.length];
        for(int i=0;i<arrs.length;i++){
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }

    public static List<Integer> StringToIntList(String[] arrs){
        List<Integer> intList=new ArrayList<Integer>();
        for(int i=0;i<arrs.length;i++){
            intList.add( Integer.parseInt(arrs[i]));
        }
        return intList;
    }

    public static <T> List<T> strToList(String str, Class<T> clazz) {
        JSONArray json = JSONArray.fromObject(str);
        JSONObject object = null;
        T t = null;
        List<T> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            object = JSONObject.fromObject(json.get(i));
            t = (T) JSONObject.toBean(object, clazz);
            list.add(t);
        }
        return list;
    }
}