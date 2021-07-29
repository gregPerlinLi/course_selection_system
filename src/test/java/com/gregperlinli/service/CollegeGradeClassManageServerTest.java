package com.gregperlinli.service;

import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.service.impl.CollegeGradeClassManageServerImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<codeCollegeGradeClassManageServer</code>进行单元测试
 *
 * @author gregperlinli
 * @since 2021-7-29
 */
public class CollegeGradeClassManageServerTest {
    /**
     * 测试<code>collegeInsert()</code>方法
     */
    @Test
    public void testCollegeInsert() {
        final College COLLEGE = new College("微电子学院");
        final CollegeGradeClassManageServer CGCMS = new CollegeGradeClassManageServerImpl();
        boolean isAdded = CGCMS.collegeInsert(COLLEGE);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>gradeInsert()</code>方法
     */
    @Test
    public void tesGradeInsert() {
        final Grade GRADE = new Grade("21级");
        final CollegeGradeClassManageServer CGCMS = new CollegeGradeClassManageServerImpl();
        boolean isAdded = CGCMS.gradeInsert(GRADE);
        System.out.println(isAdded);
    }

    /**
     * 测试<code>classesInsert()</code>方法
     */
    @Test
    public void tesClassesInsert() {
        final Classes CLASSES = new Classes("20光电信息科学与技术1班", "物理与光电工程学院", "20级");
        final CollegeGradeClassManageServer CGCMS = new CollegeGradeClassManageServerImpl();
        boolean isAdded = CGCMS.classesInsert(CLASSES);
        System.out.println(isAdded);
    }

}
