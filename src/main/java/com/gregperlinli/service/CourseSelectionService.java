package com.gregperlinli.service;

import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.pojo.Student;

import java.util.List;

/**
 * 用于处理与选课有关的事务
 *
 * @author gregPerlinLi
 * @since 2021-08-16
 */
public interface CourseSelectionService {

    /**
     * 选课事务
     *
     * @param courseId 提供所选课程的id
     * @param student 提供要进行选课操作的学生对象
     * @return 返回一个选课结果数字，若为<code>0</code>则选课失败，若为<code>1</code>则选课成功，若为<code>2</code>则该学生已选过此课程
     */
    Integer selectCourse(int courseId, Student student);

    /**
     * 放弃所选课程事务
     *
     * @param selectedCourseId 提供一个需要放弃的已选课程信息id
     * @return 凡事是否成功放弃，<code>true</code>为成功放弃，<code>false</code>则为放弃失败
     */
    boolean cancelSelection(int selectedCourseId);

    /**
     * 查询所有已开始选课的课程
     *
     * @return 返回所有可以选课的课程对象集合。如果为<code>null</code>则为没有开始选课的课程
     */
    List<Course> queryEnabledCourse();

    /**
     * 根据学生姓名查询其所有已选课程信息
     *
     * @param stuName 提供所要查询的学生姓名
     * @return 返回该学生下的所有已选课程信息对象的集合。若果为<code>null</code>则为其没有任何已选课程
     */
    List<SelectedCourse> queryStudentSelectedCourse(String stuName);

}
