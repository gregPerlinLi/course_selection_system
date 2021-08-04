package com.gregperlinli.service;

import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;

/**
 * 用于对学院/年级/班级进行管理
 *
 * @author gregperlinli
 * @since 2021-7-29
 */
public interface CollegeGradeClassManageService {

    /**
     * 新增学院操作
     *
     * @param college 提供需要新增的学院对象
     * @return 返回知否已存在该学院，<code>true</code>则为不存在同名学院，<code>false</code>则为存在同名学院
     */
    boolean collegeInsert(College college);

    /**
     * 新增年级操作
     *
     * @param grade 提供需要新增的年级对象
     * @return 返回知否已存在该年级，<code>true</code>则为不存在同名年级，<code>false</code>则为存在同名年级
     */
    boolean gradeInsert(Grade grade);

    /**
     * 新增班级操作
     *
     * @param classes 提供需要新增的班级对象
     * @return 返回知否已存在该学院，<code>true</code>则为不存在同名班级，<code>false</code>则为存在同名班级
     */
    boolean classInsert(Classes classes);

    boolean collegeUpdate(College college);

    boolean gradeUpdate(Grade grade);

    boolean classUpdate(Classes classes);

    boolean collegeDelete(int id);

    boolean gradeDelete(int id);

    boolean classDelete(int id);

}
