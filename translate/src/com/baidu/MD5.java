package com.baidu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5编码相关的类
 * 
 * @author wangjingtao
 * 
 */
public class MD5 {
    // 首先初始化一个字符数组，用来存放每个16进制字符
    private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    /**
     * 获得一个字符串的MD5值
     * 
     * @param input 输入的字符串
     * @return 输入字符串的MD5值
     * @throws UnsupportedEncodingException 
     * 
     */
    public static String md5(String input) throws UnsupportedEncodingException {
        if (input == null)
            return null;

        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes("utf-8");
            // inputByteArray是输入字符串转换得到的字节数组，更新整理
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素，通过执行最终操作完成哈希计算
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 获取文件的MD5值
     * 
     * @param file
     * @return
     */
    public static String md5(File file) {
        try {
            if (!file.isFile()) {
                System.err.println("文件" + file.getAbsolutePath() + "不存在或者不是文件");
                return null;
            }
            // 创建文件输入流
            FileInputStream in = new FileInputStream(file);
            // MD5处理输入流
            String result = md5(in);
            // 关闭文件输入流
            in.close();

            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String md5(InputStream in) {

        try {
        	// 返回指定MD5算法的对象。
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            // 创建一个字节数组
            byte[] buffer = new byte[1024];
            int read = 0;
            // 从输入流中读取字节，并将它们存储到缓冲区字节数组中
            while ((read = in.read(buffer)) != -1) {
            	// 使用指定的字节数组更新摘要
                messagedigest.update(buffer, 0, read);
            }

            in.close();
            // 计算返回哈希值的字节数组
            String result = byteArrayToHex(messagedigest.digest());

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String byteArrayToHex(byte[] byteArray) {
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        // 字符数组组合成字符串返回
        return new String(resultCharArray);

    }

}
