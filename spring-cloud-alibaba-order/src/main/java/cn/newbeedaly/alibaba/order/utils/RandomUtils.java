package cn.newbeedaly.alibaba.order.utils;

import java.util.Random;

/**
 * 随机数生成工具类
 */
public class RandomUtils {

    public static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        char[] b = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            b[i] = CHARS.charAt(random.nextInt(CHARS.length()));
        }
        return new String(b);
    }

    /**
     * 返回一个定长的随机字符串(只包含数字)
     *
     * @param length 随机数字字符串长度
     * @return 随机数字字符串
     */
    public static String generateNumberString(int length) {
        char[] b = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            b[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }
        return new String(b);
    }

}
