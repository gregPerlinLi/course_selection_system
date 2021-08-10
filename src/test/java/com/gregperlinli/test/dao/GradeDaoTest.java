package com.gregperlinli.test.dao;

import com.gregperlinli.dao.GradeDao;
import com.gregperlinli.dao.impl.GradeDaoImpl;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>GradeDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class GradeDaoTest {
    final private GradeDao gradeDao = new GradeDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Grade grade = new Grade(1, "20级");
            gradeDao.insert(conn, grade);
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
            Grade grade = new Grade(1, "21级");
            gradeDao.updateById(conn, grade);
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
            gradeDao.deleteById(conn, 1);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getGradeById()</code>方法
     */
    @Test
    public void getGradeById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Grade grade = gradeDao.getGradeById(conn, 2);
            System.out.println(grade);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getGradeByGradeName()</code>方法
     */
    @Test
    public void getGradeByGradeName() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Grade grade = gradeDao.getGradeByGradeName(conn, "20级");
            System.out.println(grade);
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
            List<Grade> grades = gradeDao.getAll(conn);
            System.out.println(grades.toString());
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
            Long count = gradeDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
