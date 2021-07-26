package com.gregperlinli.service;

/**
 * 用于进行登录操作
 *
 * @author gregperlinli
 */
public interface LoginServer {

    /**
     * 普通学生的登录操作
     *
     * @param username 要登录的学生账号名
     * @param password 登录时输入的密码
     * @return 密码是否正确或者该用户是否存在, <code>true</code>为正确或存在，<code>false</code> 为不正确或不存在
     */
    boolean studentLogin(String username, String password);

    /**
     * 管理员的登录操作
     *
     * @param username 要登录的管理员账号名
     * @param password 登录时输入的密码
     * @return 密码是否正确或者该用户是否存在, <code>true</code>为正确或存在，<code>false</code> 为不正确或不存在
     */
    boolean adminLogin(String username, String password);

}
