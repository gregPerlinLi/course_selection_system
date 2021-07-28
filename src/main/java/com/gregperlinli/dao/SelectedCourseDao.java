package com.gregperlinli.dao;

import com.gregperlinli.pojo.SelectedCourse;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的selected_course表进行增删改查处理
 *
 * @author gregperlinli
 */
public interface SelectedCourseDao {

    /**
     * 插入新的已选课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param selectedCourse 提供需要增加的已选课程信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    void insert(Connection conn, SelectedCourse selectedCourse) throws Exception;

    /**
     * 根据id来删除已选课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的已选课程对象的id值
     * @throws Exception 抛出错误
     */
    void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据课程名字来删除已选课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param course 提供要删除的已选课程对象的课程名字
     * @throws Exception 抛出错误
     */
    void deleteByCourse(Connection conn, String course) throws Exception;

    /**
     * 根据学生名字来删除已选课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param stuName 提供要删除的已选课程对象的学生名字
     * @throws Exception 抛出错误
     */
    void deleteByStuName(Connection conn, String stuName) throws Exception;

    /**
     * 根据id来更新已选课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param selectedCourse 提供修改好的的已选课程信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    void updateById(Connection conn, SelectedCourse selectedCourse) throws Exception;

    /**
     * 根据学生学号来获取已选课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param stuNum 提供所要获取已选课程的学生学号
     * @return 查询到的已选课程对象集合，若无结果则返回<code>null</code>
     */
    List<SelectedCourse> getSelectedCourseByStuNum(Connection conn, String stuNum);

    /**
     * 根据学生名字来获取已选课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param stuName 提供所要获取已选课程的学生名字
     * @return 查询到的已选课程对象集合，若无结果则返回<code>null</code>
     */
    List<SelectedCourse> getSelectedCourseByStuName(Connection conn, String stuName);

    /**
     * 根据学生选择的课程名字来获取已选课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param course 提供所要获取已选课程的学生选择的课程名字
     * @return 查询到的已选课程对象集合，若无结果则返回<code>null</code>
     */
    List<SelectedCourse> getSelectedCourseByCourse(Connection conn, String course);

    /**
     * 获取所有已选课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的已选课程对象集合，若无结果则返回<code>null</code>
     */
    List<SelectedCourse> getAll(Connection conn);

    /**
     * 获取已选课程总个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回已选课程个数（以<code>long</code>为数据类型）
     */
    Long getCount(Connection conn);
}
