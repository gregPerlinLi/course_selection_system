package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;

/**
 * 用于进行注册操作
 *
 * @author gregperlinli
 */
public interface AccountManageServer {
    /**
     * 普通学生的注册操作
     *
     * @param student 提供需要注册的学生信息
     * @return 返回知否已存在该用户，<code>true</code>则为不存在同名/同号用户，<code>false</code>则为存在同名/同号用户
     */
    boolean studentRegist(Student student);

    /**
     * 普通学生的注册操作
     *
     * @param admin 提供需要注册的管理员信息
     * @return 返回知否已存在该用户，<code>true</code>则为不存在同名/同号用户，<code>false</code>则为存在同名/同号用户
     */
    boolean adminRegist(Admin admin);
}
