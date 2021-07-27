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
     * 管理员的注册操作
     *
     * @param admin 提供需要注册的管理员信息
     * @return 返回知否已存在该用户，<code>true</code>则为不存在同名/同号用户，<code>false</code>则为存在同名/同号用户
     */
    boolean adminRegist(Admin admin);

    /**
     * 修改普通学生的账号信息
     *
     * @param student 提供带有要修改的学生的新的参数的对象 (注意：id属性一定是学生现有的id属性，且不能更改！)
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean studentUpdate(Student student);

    /**
     * 修改管理员的账号信息
     *
     * @param admin 提供带有要修改的管理员的新的参数的对象 (注意：id属性一定是学生现有的id属性，且不能更改！)
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean adminUpdate(Admin admin);

    /**
     * 删除普通学生的账号
     *
     * @param id 提供要删除的学生的id
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean studentDelete(int id);

    /**
     * 删除管理员的账号
     *
     * @param id 提供要删除的管理员的id
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean adminDelete(int id);
}