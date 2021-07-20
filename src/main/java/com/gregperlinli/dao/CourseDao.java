package com.gregperlinli.dao;

import com.gregperlinli.pojo.Course;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对数据库中的course表进行增删改查处理
 *
 * @author gregperlinli
 */
public interface CourseDao {

    /**
     * 插入新的课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param course 提供需要增加的课程信息对象（不需要写id）
     * @throws Exception 抛出错误
     *
     */
    public void insert(Connection conn, Course course) throws Exception;

    /**
     * 根据id来删除课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param id 提供要删除的课程对象的id值
     * @throws Exception 抛出错误
     */
    public void deleteById(Connection conn, int id) throws Exception;

    /**
     * 根据id来更新课程信息
     *
     * @param conn 提供数据库连接池所给的连接
     * @param course 提供修改好的的课程信息对象（不需要写id）
     * @throws Exception 抛出错误
     */
    public void updateById(Connection conn, Course course) throws Exception;

    /**
     * 根据课程名字来获取课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @param courName 提供所要获取课程的名字
     * @return 查询到的课程对象，若无结果则返回<code>null</code>
     */
    public Course getCourseByCourseName(Connection conn, String courseName);

    /**
     * 获取所有课程对象
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 查询到的课程对象集合，若无结果则返回<code>null</code>
     */
    public List<Course> getAll(Connection conn);

    /**
     * 获取课程个数
     *
     * @param conn 提供数据库连接池所给的连接
     * @return 返回课程个数（以<code>long</code>为数据类型）
     */
    public Long getCount(Connection conn);
}
