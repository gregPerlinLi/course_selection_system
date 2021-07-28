package com.gregperlinli.service;

import com.gregperlinli.pojo.Course;

public interface CourseManageServer {
    /**
     * 新增课程操作
     *
     * @param course 提供需要新增的课程对象
     * @return 返回知否已存在该课程，<code>true</code>则为不存在同名课程，<code>false</code>则为存在同名课程
     */
    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(int id);
}
