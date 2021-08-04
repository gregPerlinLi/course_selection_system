package com.gregperlinli.dao;

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

    /**
     * 测试<code>insert()</code>方法
     */
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testUpdate() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testDeleteById() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testDeleteByCollege() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testDeleteByGrade() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetById() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetByStuNum() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetByCollege() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetByGrade() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetAll() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
    public void testGetCount() {
        Connection conn = null;
        try{
            ClassesDao classesDao = new ClassesDaoImpl();
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
