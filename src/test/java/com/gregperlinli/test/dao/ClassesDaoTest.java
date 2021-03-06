package com.gregperlinli.test.dao;

import com.gregperlinli.dao.ClassesDao;
import com.gregperlinli.dao.impl.ClassesDaoImpl;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

/**
 * 用于对<code>ClassesDao</code>进行单元测试
 *
 * @author gregperlinli
 */
public class ClassesDaoTest {
    final private ClassesDao classesDao = new ClassesDaoImpl();

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Classes classes = new Classes(1, "20电子科学与技术1班", "物理与光电工程学院", "20级");
            classesDao.insert(conn, classes);
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
            Classes classes = new Classes(1, "21电子科学与技术2班", "物理与光电工程学院", "21级");
            classesDao.updateById(conn, classes);
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
            classesDao.deleteById(conn, 1);
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
            classesDao.deleteByCollege(conn, "计算机学院");
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
            classesDao.deleteByGrade(conn, "22级");
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }


    /**
     * 测试<code>getClassById()</code>方法
     */
    @Test
    public void getClassById() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Classes classes = classesDao.getClassById(conn, 3);
            System.out.println(classes);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getClassByClassName()</code>方法
     */
    @Test
    public void getClassByClassName() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            Classes classes = classesDao.getClassByClassName(conn, "20电子科学与技术1班");
            System.out.println(classes);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getClassesByCollege()</code>方法
     */
    @Test
    public void getClassesByCollege() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Classes> classesList = classesDao.getClassesByCollege(conn, "物理与光电工程学院");
            System.out.println(classesList);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /**
     * 测试<code>getClassesByGrade()</code>方法
     */
    @Test
    public void getClassesByGrade() {
        Connection conn = null;
        try{
            conn = JDBCUtils.getConnectionWithPool();
            List<Classes> classesList = classesDao.getClassesByGrade(conn, "20级");
            System.out.println(classesList);
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
            List<Classes> classess = classesDao.getAll(conn);
            System.out.println(classess.toString());
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
            Long count = classesDao.getCount(conn);
            System.out.println(count);
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

}
