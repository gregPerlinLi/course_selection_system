package com.gregperlinli.test.service;

import com.gregperlinli.pojo.Course;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.service.impl.CourseManageServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

/**
 * 对<code>CourseManageService</code>进行单元测试
 *
 * @author gregperlinli
 * @since 2021-7-28
 */
public class CourseManageServiceTest {
    private final CourseManageService courseManageService = new CourseManageServiceImpl();

    /**
     * 测试<code>insertCourse()</code>方法
     */
    @Test
    public void insertCourse() {
        final Course course = new Course("大学英语",
                Date.valueOf("2021-8-30"),
                Time.valueOf("12:00:00"),
                120);
        boolean isAdded = courseManageService.addCourse(course);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>updateCourse()</code>方法
     */
    @Test
    public void updateCourse() {
        final Course course = new Course(3,
                "大学英语",
                Date.valueOf("2021-8-31"),
                Time.valueOf("11:45:00"),
                150);
        boolean isUpdated = courseManageService.updateCourse(course);
        System.out.println(isUpdated);
    }

    /**
     * 测试<code>deleteCourse()</code>方法
     */
    @Test
    public void deleteCourse() {
        boolean isDeleted = courseManageService.deleteCourse(3);
        System.out.println(isDeleted);
    }

    /**
     * 测试getCourseById()方法
     */
    @Test
    void getCourseById() {
        System.out.println(courseManageService.getCourseById(2));
    }

    /**
     * 测试getAllCourse()方法
     */
    @Test
    void getAllCourse() {
        System.out.println(courseManageService.getAllCourse());
    }

    /**
     * 测试getAllSelectedCourse()方法
     */
    @Test
    void getAllSelectedCourse(){
        System.out.println(courseManageService.getAllSelectedCourse());
    }
}
