package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;

import java.util.List;

/**
 * 用于进行用户修改与管理操作
 *
 * @author gregperlinli
 * @since 2021-7-28
 */
public interface AccountManageService {
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
     * 修改普通学生的账号信息<br>
     * <span style='color:yellow;'>注意：在修改学生的同时会将原来该学生下的所有已选课程信息完全修改</span>
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
     * 删除普通学生的账号<br>
     * <span style='color:yellow;'>注意：在删除学生的同时会将原来该学生下的所有已选课程信息完全清除，请谨慎使用！</span>
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

    /**
     * 获取所有学生对象
     *
     * @return 返回装有所有学生对象的集合，如果为<code>null</code>则没有学生
     */
    List<Student> getAllStudent();

    /**
     * 根据id查询学生操作
     *
     * @param id 提供要查询的学生id
     * @return 返回查询到的学生对象，如果为<code>null</code>则不存在该对象
     */
    Student getStuById(int id);

    /**
     * 获取所有管理员对象
     *
     * @return 返回装有所有管理员对象的集合，如果为<code>null</code>则没有管理员
     */
    List<Admin> getAllAdmin();

    /**
     * 根据id查询管理员操作
     *
     * @param id 提供要查询的管理员id
     * @return 返回查询到的管理员对象，如果为<code>null</code>则不存在该对象
     */
    Admin getAdminById(int id);

    /**
     * 检查是否存在该学生姓名
     *
     * @param username 提供需要检查的姓名
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean existStudentUsername(String username);

    /**
     * 检查是否存在该学号
     *
     * @param stuNum 提供需要检查的学号
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean existStudentStuNum(String stuNum);

    /**
     * 检查是否存在该管理员用户名
     *
     * @param username 提供需要检查的用户名
     * @return 返回是否存在该用户，<code>true</code>则为存在该用户，<code>false</code>则为不存在该用户
     */
    boolean existAdminUsername(String username);
}
