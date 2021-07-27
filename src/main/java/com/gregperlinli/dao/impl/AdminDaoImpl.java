package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.pojo.Admin;

import java.sql.Connection;
import java.util.List;

public class AdminDaoImpl extends BaseDAO<Admin> implements AdminDao {
    @Override
    public void insert(Connection conn, Admin admin) throws Exception {
        String sql = "insert into admin(username, password)values(?,?)";
        update(conn, sql, admin.getUsername(), admin.getPassword());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from admin where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Admin admin) throws Exception {
        String sql = "update admin set username = ?, password = ? where id = ?";
        update(conn, sql, admin.getUsername(), admin.getPassword(), admin.getId());
    }

    @Override
    public Admin getAdmById(Connection conn, int id) {
        String sql = "select id, username, password from admin where id = ?";
        return getQuery(conn, sql, id);
    }

    @Override
    public Admin getAdmByUsername(Connection conn, String username) {
        String sql = "select id, username, password from admin where username = ?";
        return getQuery(conn, sql, username);
    }

    @Override
    public List<Admin> getAll(Connection conn) {
        String sql = "select id, username, password from admin";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from admin";
        return getValue(conn, sql);
    }
}
