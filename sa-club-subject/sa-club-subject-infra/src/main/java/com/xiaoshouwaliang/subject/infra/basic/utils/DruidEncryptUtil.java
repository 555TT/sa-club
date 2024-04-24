package com.xiaoshouwaliang.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author 小手WA凉
 * @date 2024-04-16
 */
public class DruidEncryptUtil {
    private static String privateKey;
    private static String publicKey;
    static {
        try {
            String[] strings = ConfigTools.genKeyPair(512);
            privateKey=strings[0];
            publicKey=strings[1];
            System.out.println("privateKey:"+privateKey);
            System.out.println("publicKey:"+publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }
    public static String encrypt(String plainText){
        try {
            return ConfigTools.encrypt(privateKey,plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String decrypt(String encrypt){
        try {
            return ConfigTools.decrypt(publicKey,encrypt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String password = encrypt("whr666");
//        String url = encrypt("jdbc:mysql://182.92.85.80:3306/sa-club?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false");
        String username = encrypt("root");
        System.out.println("encrypt:"+password);
//        System.out.println("url:"+url);
        System.out.println("username:"+username);
    }
}
