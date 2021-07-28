package com.gregperlinli.service.impl;

import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.dao.impl.CourseDaoImpl;
import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.service.CourseManageServer;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseManageServerImpl implements CourseManageServer {
    Connection conn = null;

    @Override
    public boolean addCourse(Course course) {
        final CourseDao COURSE_DAO = new CourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( COURSE_DAO.getCourseByCourseName(conn, course.getCourseName()) == null) {
                COURSE_DAO.insert(conn, course);
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
    public boolean updateCourse(Course course) {
        final CourseDao COURSE_DAO = new CourseDaoImpl();
        final SelectedCourseDao SELECTED_COURSE_DAO = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Course currentCourse = COURSE_DAO.getCourseById(conn, course.getId());
            if ( currentCourse != null) {
                // 先删除该课程下所有已选课程信息
                SELECTED_COURSE_DAO.deleteByCourse(conn, currentCourse.getCourseName());
                // 然乎再修改课程信息
                COURSE_DAO.updateById(conn, course);
                // 提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        final CourseDao COURSE_DAO = new CourseDaoImpl();
        final SelectedCourseDao SELECTED_COURSE_DAO = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Course currentCourse = COURSE_DAO.getCourseById(conn, id);
            if ( currentCourse != null) {
                // 先删除该课程下所有已选课程信息
                SELECTED_COURSE_DAO.deleteByCourse(conn, currentCourse.getCourseName());
                // 然乎再删除课程
                COURSE_DAO.deleteById(conn, id);
                // 提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }
}
