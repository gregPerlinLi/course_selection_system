package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.CollegeDao;
import com.gregperlinli.pojo.College;

import java.sql.Connection;
import java.util.List;

/**
 * <code>CollegeDao</code>的实现类
 *
 * @author gregperlinli
 * @see com.gregperlinli.dao.BaseDAO
 * @see com.gregperlinli.dao.CollegeDao
 * @since 2021-7-21
 */
public class CollegeDaoImpl extends BaseDAO<College> implements CollegeDao {
    @Override
    public void insert(Connection conn, College college) throws Exception {
        String sql = "insert into college(college_name)values(?)";
        update(conn, sql, college.getCollegeName());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from college where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, College college) throws Exception {
        String sql = "update college set college_name = ? where id = ?";
        update(conn, sql, college.getCollegeName(), college.getId());
    }

    @Override
    public College getCollegeById(Connection conn, int id) {
        String sql = "select id, college_name collegeName from college where id = ?";
        return getQuery(conn, sql, id);
    }

    @Override
    public College getCollegeByCollegeName(Connection conn, String collegeName) {
        String sql = "select id, college_name collegeName from college where college_name = ?";
        return getQuery(conn, sql, collegeName);
    }

    @Override
    public List<College> getAll(Connection conn) {
        String sql = "select id, college_name collegeName from college";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from college";
        return getValue(conn, sql);
    }
}
