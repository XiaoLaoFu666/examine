package com.huang.examine.redis;

/**
 * @Author: HuangJunHao
 * @Date: 2020/3/7 20:22
 */
public class TeacherKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    private TeacherKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static TeacherKey token = new TeacherKey(TOKEN_EXPIRE, "the");
    public static TeacherKey getById = new TeacherKey(0, "userId");
}
