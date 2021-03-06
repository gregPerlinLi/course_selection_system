package com.gregperlinli.test.service;

import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.service.CourseSelectionService;
import com.gregperlinli.service.impl.CourseSelectionServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 对<code>courseSelectionService</code>进行单元测试
 *
 * @author gregPerlinLi
 * @since 2021-08-16
 */
class CourseSelectionServiceTest {
    private final CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();

    @Test
    void selectCourse() {
    }

    @Test
    void cancelSelection() {
    }

    /**
     * 测试<code>queryEnableCourse</code>方法
     */
    @Test
    void queryEnableCourse() {
        List<Course> courses = courseSelectionService.queryEnabledCourse();
        System.out.println(courses);
    }

    /**
     * 测试<coe>queryStudentSelectedCourse()</coe>方法
     */
    @Test
    void queryStudentSelectedCourse() {
        List<SelectedCourse> selectedCourses = courseSelectionService.queryStudentSelectedCourse("李浩霖");
        System.out.println(selectedCourses);
    }
}