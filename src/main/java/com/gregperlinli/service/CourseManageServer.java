package com.gregperlinli.service;

import com.gregperlinli.pojo.Course;

/**
 * 用于进行课程管理
 *
 * @author gregperlinli
 * @since 2021-7-28
 */
public interface CourseManageServer {
    /**
     * 新增课程操作
     *
     * @param course 提供需要新增的课程对象
     * @return 返回知否已存在该课程，<code>true</code>则为不存在同名课程，<code>false</code>则为存在同名课程
     */
    boolean addCourse(Course course);

    /**
     * 修改课程操作<br>
     * <span style='color:yellow;'>注意：在修改课程的同时会将原来该课程下的所有已选课程信息完全清除，请谨慎使用！</span>
     *
     * @param course 提供带有要修改的课程的新的参数的对象 (注意：id属性一定是课程现有的id属性，且不能更改！)
     * @return 返回知否已存在该课程，<code>true</code>则为存在该课程，<code>false</code>则为不存在该课程
     */
    boolean updateCourse(Course course);

    /**
     * 删除课程操作<br>
     * <span style='color:yellow;'>注意：在删除课程的同时会将原来该课程下的所有已选课程信息完全清除，请谨慎使用！</span>
     *
     * @param id 提供要删除的课程id
     * @return 返回知否已存在该课程，<code>true</code>则为存在该课程，<code>false</code>则为不存在该课程
     */
    boolean deleteCourse(int id);
}
