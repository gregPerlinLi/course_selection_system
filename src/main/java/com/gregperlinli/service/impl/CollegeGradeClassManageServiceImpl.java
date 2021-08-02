package com.gregperlinli.service.impl;

import com.gregperlinli.dao.ClassesDao;
import com.gregperlinli.dao.CollegeDao;
import com.gregperlinli.dao.GradeDao;
import com.gregperlinli.dao.impl.ClassesDaoImpl;
import com.gregperlinli.dao.impl.CollegeDaoImpl;
import com.gregperlinli.dao.impl.GradeDaoImpl;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author gregperlinli
 * @see CollegeGradeClassManageService
 * @since 2021-7-29
 */
public class CollegeGradeClassManageServiceImpl implements CollegeGradeClassManageService {
    Connection conn = null;

    @Override
    public boolean collegeInsert(College college) {
        final CollegeDao COLLEGE_DAO = new CollegeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( COLLEGE_DAO.getCollegeByCollegeName(conn, college.getCollegeName()) == null ) {
                COLLEGE_DAO.insert(conn, college);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean gradeInsert(Grade grade) {
        final GradeDao GRADE_DAO = new GradeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( GRADE_DAO.getGradeByGradeName(conn, grade.getGradeName()) == null ) {
                GRADE_DAO.insert(conn, grade);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean classesInsert(Classes classes) {
        final ClassesDao CLASSES_DAO = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( CLASSES_DAO.getClassByClassName(conn, classes.getClassesName()) == null ) {
                CLASSES_DAO.insert(conn, classes);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean collegeUpdate(College college) {
        return false;
    }

    @Override
    public boolean gradeUpdate(Grade grade) {
        return false;
    }

    @Override
    public boolean classesUpdate(Classes classes) {
        return false;
    }

    @Override
    public boolean collegeDelete(int id) {
        return false;
    }

    @Override
    public boolean gradeDelete(int id) {
        return false;
    }

    @Override
    public boolean classesDelete(int id) {
        return false;
    }
}
