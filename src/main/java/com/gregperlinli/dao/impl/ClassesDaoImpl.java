package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.ClassesDao;
import com.gregperlinli.pojo.Classes;

import java.sql.Connection;
import java.util.List;

/**
 * @author gregperlinli
 * @see com.gregperlinli.dao.BaseDAO
 * @see com.gregperlinli.dao.ClassesDao
 * @since 2021-7-21
 */
public class ClassesDaoImpl extends BaseDAO<Classes> implements ClassesDao {
    @Override
    public void insert(Connection conn, Classes classes) throws Exception {
        String sql = "insert into classes(classes_name, college, grade)values(?,?,?)";
        update(conn, sql, classes.getClassesName(), classes.getCollege(), classes.getGrade());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from classes where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Classes classes) throws Exception {
        String sql = "update classes set classes_name = ?, college = ?, grade = ? where id = ?";
        update(conn, sql, classes.getClassesName(), classes.getCollege(), classes.getGrade(), classes.getId());
    }

    @Override
    public Classes getClassById(Connection conn, int id) {
        String sql = "select id, classes_name classesName, college, grade from classes where id = ? ";
        return getQuery(conn, sql, id);
    }

    @Override
    public Classes getClassByClassName(Connection conn, String classesName) {
        String sql = "select id, classes_name classesName, college, grade from classes where classes_name = ? ";
        return getQuery(conn, sql, classesName);
    }

    @Override
    public List<Classes> getClassesByCollege(Connection conn, String college) {
        String sql = "select id, classes_name classesName, college, grade from classes where college = ? ";
        return getMultiQuery(conn, sql, college);
    }

    @Override
    public List<Classes> getClassesByGrade(Connection conn, String grade) {
        String sql = "select id, classes_name classesName, college, grade from classes where grade = ? ";
        return getMultiQuery(conn, sql, grade);
    }

    @Override
    public List<Classes> getAll(Connection conn) {
        String sql = "select id, classes_name classesName, college, grade from classes";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from classes";
        return getValue(conn, sql);
    }
}
