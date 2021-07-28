package com.gregperlinli.service;

import com.gregperlinli.pojo.Course;
import com.gregperlinli.service.impl.CourseManageServerImpl;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

/**
 * 对<code>CourseManageServer</code>进行单元测试
 *
 * @author gregperlinli
 * @since 2021-7-28
 */
public class CourseManageServerTest {
    /**
     * 测试<code>insertCourse()</code>方法
     */
    @Test
    public void testInsertCourse() {
        final Course COURSE = new Course("大学英语",
                Date.valueOf("2021-8-30"),
                Time.valueOf("12:00:00"),
                120);
        final CourseManageServer COURSE_MANAGE_SERVER = new CourseManageServerImpl();
        boolean isAdded = COURSE_MANAGE_SERVER.addCourse(COURSE);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>updateCourse()</code>方法
     */
    @Test
    public void testUpdateCourse() {
        final Course COURSE = new Course(3,
                "大学物理",
                Date.valueOf("2021-8-29"),
                Time.valueOf("11:00:00"),
                150);
        final CourseManageServer COURSE_MANAGE_SERVER = new CourseManageServerImpl();
        boolean isUpdated = COURSE_MANAGE_SERVER.updateCourse(COURSE);
        System.out.println(isUpdated);
    }

    /**
     * 测试<code>deleteCourse()</code>方法
     */
    @Test
    public void testDeleteCourse() {
        final CourseManageServer COURSE_MANAGE_SERVER = new CourseManageServerImpl();
        boolean isDeleted = COURSE_MANAGE_SERVER.deleteCourse(3);
        System.out.println(isDeleted);
    }
}
