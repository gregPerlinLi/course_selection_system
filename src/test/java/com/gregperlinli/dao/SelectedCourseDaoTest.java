package com.gregperlinli.dao;

import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>SelectedCourseDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class SelectedCourseDaoTest {

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            SelectedCourse selectedCourse = new SelectedCourse(1, "3120007215", "李浩霖", "高等数学");
            selectedCourseDao.insert(conn, selectedCourse);
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
    public void testUpdate() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            SelectedCourse selectedCourse = new SelectedCourse(1, "3121007001", "小明", "大学物理");
            selectedCourseDao.updateById(conn, selectedCourse);
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
    public void testDelete() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            selectedCourseDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getSelectedCourseByStuNum()</code>方法
     */
    @Test
    public void testGetByStuNum() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<SelectedCourse> selectedCourseList = selectedCourseDao.getSelectedCourseByStuNum(conn, "3120007215");
            System.out.println(selectedCourseList);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getSelectedCourseByStuName()</code>方法
     */
    @Test
    public void testGetByStuName() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<SelectedCourse> selectedCourseList = selectedCourseDao.getSelectedCourseByStuName(conn, "李浩霖");
            System.out.println(selectedCourseList);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getSelectedCourseByCourse()</code>方法
     */
    @Test
    public void testGetByCourse() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<SelectedCourse> selectedCourseList = selectedCourseDao.getSelectedCourseByCourse(conn, "高等数学");
            System.out.println(selectedCourseList);
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
    public void testGetAll() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<SelectedCourse> selectedCourses = selectedCourseDao.getAll(conn);
            System.out.println(selectedCourses.toString());
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
    public void testGetCount() {
        Connection conn = null;
        try{
            SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Long count = selectedCourseDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
