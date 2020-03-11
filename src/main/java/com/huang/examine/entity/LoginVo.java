package com.huang.examine.entity;

/**
 * @author Mr.huang
 * 统一登录用户类型
 */

public class LoginVo {
    private String username;
    private String password;
    /**
     * 用户身份
     * */
    private String identity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
