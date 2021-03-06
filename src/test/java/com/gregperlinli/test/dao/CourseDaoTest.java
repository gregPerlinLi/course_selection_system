package com.gregperlinli.test.dao;

import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.dao.impl.CourseDaoImpl;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * 用于对<code>CourseDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class CourseDaoTest {
    final private CourseDao courseDao = new CourseDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Course course = new Course(1, "高等数学", Date.valueOf("2021-9-1") , Time.valueOf("12:00:00"), 100);
            courseDao.insert(conn, course);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>deleteById()</code>方法
     */
    @Test
    void deleteById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            courseDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>updateById()</code>方法
     */
    @Test
    void updateById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Course course = new Course(1, "大学物理", Date.valueOf("2021-8-30"), Time.valueOf("12:30:00"), 80);
            courseDao.updateById(conn, course);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>updateCurrentStu()</code>方法
     */
    @Test
    void updateCurrentStu() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            courseDao.updateCurrentStu(conn, 2, 80);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getCourseById()</code>方法
     */
    @Test
    void getCourseById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Course course = courseDao.getCourseById(conn, 2);
            System.out.println(course);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getCourseByCourseName()</code>方法
     */
    @Test
    void getCourseByCourseName() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Course course = courseDao.getCourseByCourseName(conn, "高等数学");
            System.out.println(course);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getAll()</code>方法
     */
    @Test
    void getAll() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Course> courses = courseDao.getAll(conn);
            System.out.println(courses.toString());
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getCount()</code>方法
     */
    @Test
    void getCount() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Long count = courseDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
