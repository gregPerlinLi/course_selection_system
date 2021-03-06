package com.gregperlinli.test.dao;

import com.gregperlinli.dao.StudentDao;
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
    final private StudentDao studentDao = new StudentDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Student student = new Student(1, "3120007215", "李浩霖", "123456", "物理与光电工程学院", "20", "20电子科学与技术3班");
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
    public void updateById() {
        Connection conn = null;
        try{
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
    public void deleteById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            studentDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>deleteByCollege()</code>方法
     */
    @Test
    public void deleteByCollege() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            studentDao.deleteByCollege(conn, "计算机学院");
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>deleteByGrade()</code>方法
     */
    @Test
    public void deleteByGrade() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            studentDao.deleteByGrade(conn, "22级");
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>deleteByClass()</code>方法
     */
    @Test
    public void deleteByClass() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            studentDao.deleteByClass(conn, "21计算机科学与技术1班");
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getStuById()</code>方法
     */
    @Test
    public void getStuById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Student student = studentDao.getStuById(conn, 2);
            System.out.println(student);
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
    public void getStuByStuNum() {
        Connection conn = null;
        try{
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
    public void getStuByUsername() {
        Connection conn = null;
        try{
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
     * 测试<code>getStuByCollege()</code>方法
     */
    @Test
    public void getStuByCollege() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Student> students = studentDao.getStuByCollege(conn, "物理与光电工程学院");
            System.out.println(students.toString());
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getStuByGrade()</code>方法
     */
    @Test
    public void getStuByGrade() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Student> students = studentDao.getStuByGrade(conn, "20级");
            System.out.println(students.toString());
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getStuByClasses()</code>方法
     */
    @Test
    public void getStuByClasses() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Student> students = studentDao.getStuByStuClass(conn, "20电子科学与技术3班");
            System.out.println(students.toString());
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
    public void getAll() {
        Connection conn = null;
        try{
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
    public void getCount() {
        Connection conn = null;
        try{
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
