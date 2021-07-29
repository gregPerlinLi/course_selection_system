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
public interface CollegeGradeClassManageServer {

    boolean collegeInsert(College college);

    boolean gradeInsert(Grade grade);

    boolean classesInsert(Classes classes);

    boolean collegeUpdate(College college);

    boolean gradeUpdate(Grade grade);

    boolean classesUpdate(Classes classes);

    boolean collegeDelete(int id);

    boolean gradeDelete(int id);

    boolean classesDelete(int id);

}
