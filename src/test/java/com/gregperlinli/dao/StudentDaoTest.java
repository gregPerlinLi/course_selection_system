package com.gregperlinli.dao;

import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>StudentDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class StudentDaoTest {
    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Student student = new Student(1, "3120007215", "李浩霖", "123456", "物理与光电工程学院", "20", "电子科学与技术3班");
            studentDao.insert(conn, student);
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
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Student student = new Student(1, "3121007001", "小明", "111111", "I don't know", "21", "I don't know");
            studentDao.updateById(conn, student);
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
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            studentDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getStuByStuNum()</code>方法
     */
    @Test
    public void testGetByStuNum() {
        Connection conn = null;
        try{
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Student student = studentDao.getStuByStuNum(conn, "3120007215");
            System.out.println(student);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getStuByUsername()</code>方法
     */
    @Test
    public void testGetByUsername() {
        Connection conn = null;
        try{
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Student student = studentDao.getStuByUsername(conn, "李浩霖");
            System.out.println(student);
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
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            List<Student> students = studentDao.getAll(conn);
            System.out.println(students.toString());
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
            StudentDao studentDao = new StudentDaoImpl();
            conn = JDBCUtils.getConnectionWithPool();
            Long count = studentDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
