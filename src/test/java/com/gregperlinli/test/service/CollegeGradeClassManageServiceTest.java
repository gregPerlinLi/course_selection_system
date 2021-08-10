package com.gregperlinli.test.service;

import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.service.impl.CollegeGradeClassManageServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<codeCollegeGradeClassManageServer</code>进行单元测试
 *
 * @author gregperlinli
 * @since 2021-7-29
 */
public class CollegeGradeClassManageServiceTest {
    /**
     * 测试<code>collegeInsert()</code>方法
     */
    @Test
    public void collegeInsert() {
        final College college = new College("微电子学院");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isAdded = cgcms.collegeInsert(college);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>gradeInsert()</code>方法
     */
    @Test
    public void gradeInsert() {
        final Grade grade = new Grade("21级");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isAdded = cgcms.gradeInsert(grade);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>classesInsert()</code>方法
     */
    @Test
    public void classesInsert() {
        final Classes classes = new Classes("20光电信息科学与技术1班", "物理与光电工程学院", "20级");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isAdded = cgcms.classInsert(classes);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>collegeUpdate()</code>方法
     */
    @Test
    public void collegeUpdate() {
        final College college = new College(2, "物电学院");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isUpdate = cgcms.collegeUpdate(college);
        System.out.println(isUpdate);
    }


    /**
     * 测试<code>gradeUpdate()</code>方法
     */
    @Test
    public void gradeUpdate() {
        final Grade grade = new Grade(2, "19级");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isUpdate = cgcms.gradeUpdate(grade);
        System.out.println(isUpdate);
    }


    /**
     * 测试<code>classUpdate()</code>方法
     */
    @Test
    public void classUpdate() {
        final Classes classes = new Classes(3,"20电子科学与技术3班", "物理与光电工程学院", "20级");
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isUpdate = cgcms.classUpdate(classes);
        System.out.println(isUpdate);
    }

    /**
     * 测试<code>collegeDelete()</code>方法
     */
    @Test
    public void collegeDelete() {
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isDelete = cgcms.collegeDelete(5);
        System.out.println(isDelete);
    }


    /**
     * 测试<code>gradeDelete()</code>方法
     */
    @Test
    public void gradeDelete() {
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isDelete = cgcms.gradeDelete(5);
        System.out.println(isDelete);
    }


    /**
     * 测试<code>classDelete()</code>方法
     */
    @Test
    public void classDelete() {
        final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
        boolean isDelete = cgcms.classDelete(12);
        System.out.println(isDelete);
    }

}
