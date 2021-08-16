package com.gregperlinli.service;

import com.gregperlinli.pojo.Course;

import java.util.List;

/**
 * 用于处理课程管理相关的事务
 *
 * @author gregperlinli
 * @since 2021-7-28
 */
public interface CourseManageService {
    /**
     * 新增课程事务
     *
     * @param course 提供需要新增的课程对象
     * @return 返回知否已存在该课程，<code>true</code>则为不存在同名课程，<code>false</code>则为存在同名课程
     */
    boolean addCourse(Course course);

    /**
     * 修改课程事务<br>
     * <span style='color:yellow;'>注意：在修改课程的同时会将原来该课程下的所有已选课程信息进行修改</span>
     *
     * @param course 提供带有要修改的课程的新的参数的对象 (注意：id属性一定是课程现有的id属性，且不能更改！)
     * @return 返回知否已存在该课程，<code>true</code>则为存在该课程，<code>false</code>则为不存在该课程
     */
    boolean updateCourse(Course course);

    /**
     * 删除课程事务<br>
     * <span style='color:yellow;'>注意：在删除课程的同时会将原来该课程下的所有已选课程信息完全清除，请谨慎使用！</span>
     *
     * @param id 提供要删除的课程id
     * @return 返回知否已存在该课程，<code>true</code>则为存在该课程，<code>false</code>则为不存在该课程
     */
    boolean deleteCourse(int id);

    /**
     * 根据id查询课程事务
     *
     * @param id 提供要查询的课程id
     * @return 返回查询到的课程对象，如果为<code>null</code>则不存在该对象
     */
    Course getCourseById(int id);

    /**
     * 检查是否存在该课程名事务
     *
     * @param courseName 提供需要检查的课程名称
     * @return 返回是否存在该课程，<code>true</code>则为存在该课程，<code>false</code>则为不存在该课程
     */
    boolean existCourseName(String courseName);

    /**
     * 查询所有课程事务
     * @return 返回所有课程对象的集合，如果为<code>null</code>则没有课程
     */
    List<Course> getAllCourse();
}
