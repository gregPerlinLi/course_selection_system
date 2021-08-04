package com.gregperlinli.dao;

import com.gregperlinli.pojo.Student;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的student表进行增删改查处理
 *
 * @author gregperlinli
 * @since 2021-7-21
 */
public interface StudentDao {

    /**
     * 插入新的学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param student 提供需要增加的学生信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    void insert(Connection conn, Student student) throws Exception;

    /**
     * 根据id来删除学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的学生对象的id值
     * @throws Exception 抛出错误
     */
    void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据学院名来删除学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param college 提供要删除的学生对象所在的学院名
     * @throws Exception 抛出错误
     */
    void deleteByCollege(Connection conn, String college) throws Exception;

    /**
     * 根据年级来删除学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param grade 提供要删除的学生对象所在的年级
     * @throws Exception 抛出错误
     */
    void deleteByGrade(Connection conn, String grade) throws Exception;

    /**
     * 根据班级名来删除学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param className 提供要删除的学生对象所在的班级名
     * @throws Exception 抛出错误
     */
    void deleteByClass(Connection conn, String className) throws Exception;

    /**
     * 根据id来更新学生信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param student 提供修改好的的学生信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    void updateById(Connection conn, Student student) throws Exception;

    /**
     * 根据id来获取学生对象
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供所要获取学生的id
     * @return 查询到的学生对象，若无结果则返回<code>null</code>
     */
    Student getStuById(Connection conn, int id);

    /**
     * 根据学生学号来获取学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param stuNum 提供所要获取学生的学号
     * @return 查询到的学生对象，若无结果则返回<code>null</code>
     */
    Student getStuByStuNum(Connection conn, String stuNum);

    /**
     * 根据学生名字来获取学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param username 提供所要获取学生的名字
     * @return 查询到的学生对象，若无结果则返回<code>null</code>
     */
    Student getStuByUsername(Connection conn, String username);

    /**
     * 根据学生所在学院来获取学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param college 提供所要获取学生的所在学院
     * @return 查询到的学生对象集合，若无结果则返回<code>null</code>
     */
    List<Student> getStuByCollege(Connection conn, String college);

    /**
     * 根据学生所在年级来获取学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param grade 提供所要获取学生的所在年级
     * @return 查询到的学生对象，若无结果则返回<code>null</code>
     */
    List<Student> getStuByGrade(Connection conn, String grade);

    /**
     * 根据学生所在班级来获取学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param stuClass 提供所要获取学生的所在班级
     * @return 查询到的学生对象，若无结果则返回<code>null</code>
     */
    List<Student> getStuByStuClass(Connection conn, String stuClass);

    /**
     * 获取所有学生对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的学生对象集合，若无结果则返回<code>null</code>
     */
    List<Student> getAll(Connection conn);

    /**
     * 获取学生个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回学生个数（以<code>long</code>为数据类型）
     */
    Long getCount(Connection conn);
}
