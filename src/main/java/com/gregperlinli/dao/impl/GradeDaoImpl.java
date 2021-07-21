package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.GradeDao;
import com.gregperlinli.pojo.Grade;

import java.sql.Connection;
import java.util.List;

public class GradeDaoImpl extends BaseDAO<Grade> implements GradeDao {
    @Override
    public void insert(Connection conn, Grade grade) throws Exception {
        String sql = "insert into grade(grade_name)values(?)";
        update(conn, sql, grade.getGradeName());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from grade where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Grade grade) throws Exception {
        String sql = "update grade set grade_name = ? where id = ?";
        update(conn, sql, grade.getGradeName(), grade.getId());
    }

    @Override
    public Grade getGradeByGradeName(Connection conn, String gradeName) {
        String sql = "select id, grade_name gradeName from grade where grade_name = ?";
        return getQuery(conn, sql, gradeName);
    }

    @Override
    public List<Grade> getAll(Connection conn) {
        String sql = "select id, grade_name gradeName from grade";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from grade";
        return getValue(conn, sql);
    }
}
