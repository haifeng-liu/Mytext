package com.demo.liuhf.mybaselibrarys.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：MD5加密，解密
 * 修改历史：
 */

public class MD5Utils {
    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'B', 'C', 'D', 'B', 'O', 'T', 'H', 'O', 'F', 'Y', 'O', 'U' };

    /** 对文件加密 */
    public static String encode(File file) {
        FileInputStream in = null;
        MessageDigest md5 = null;
        try {
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return toHex(md5.digest());
    }

    /** 对字符串加密 */
    public static String encode(String arg) {
        if (arg == null) {
            arg = "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(arg.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex(md5.digest());
    }

    private static String toHex(byte[] bytes) {
        StringBuffer str = new StringBuffer(32);
        for (byte b : bytes) {
            str.append(hexDigits[(b & 0xf0) >> 4]);
            str.append(hexDigits[(b & 0x0f)]);
        }
        return str.toString();
    }

    public static String md5_3(String arg) {
        if (arg == null) {
            arg = "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] a = md5.digest(arg.getBytes("utf-8"));
            a = md5.digest(a);
            md5.update(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex(md5.digest());
    }

    /**
     * MD5加密    以byte数组表示的字符串
     * 结果与encode(String arg)返回的一样
     *
     * @param str 目标byte数组
     * @return MD5加密后的字符串
     */
    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }
    public static String getMD5String(byte[] bytes) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex(md5.digest());
    }

    public static String toHex2(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String shex =    Integer.toHexString(bytes[i] & 0xff);
            if(shex.length()==1){
                shex='0'+shex;
            }
            sb.append(shex);
        }
        return sb.toString().toUpperCase();
    }

    public static String getMD5StringQGen(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex2(md5.digest());
    }


}
