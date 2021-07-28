package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.pojo.SelectedCourse;

import java.sql.Connection;
import java.util.List;

public class SelectedCourseDaoImpl extends BaseDAO<SelectedCourse> implements SelectedCourseDao {
    @Override
    public void insert(Connection conn, SelectedCourse selectedCourse) throws Exception {
        String sql = "insert into selected_course(stu_num, stu_name, course)values(?,?,?)";
        update(conn, sql, selectedCourse.getStuNum(), selectedCourse.getStuName(), selectedCourse.getCourse());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from selected_course where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void deleteByCourse(Connection conn, String course) throws Exception {
        String sql = "delete from selected_course where course = ?";
        update(conn, sql, course);
    }

    @Override
    public void deleteByStuName(Connection conn, String stuName) throws Exception {
        String sql = "delete from selected_course where stu_name = ?";
        update(conn, sql, stuName);
    }

    @Override
    public void updateById(Connection conn, SelectedCourse selectedCourse) throws Exception {
        String sql = "update selected_course set stu_num = ?, stu_name = ?, course = ? where id = ?";
        update(conn, sql, selectedCourse.getStuNum(), selectedCourse.getStuName(), selectedCourse.getCourse(), selectedCourse.getId());
    }

    @Override
    public List<SelectedCourse> getSelectedCourseByStuNum(Connection conn, String stuNum) {
        String sql = "select id, stu_num stuNum, stu_name stuName, course from selected_course where stu_num = ?";
        return getMultiQuery(conn, sql, stuNum);
    }

    @Override
    public List<SelectedCourse> getSelectedCourseByStuName(Connection conn, String stuName) {
        String sql = "select id, stu_num stuNum, stu_name stuName, course from selected_course where stu_name = ?";
        return getMultiQuery(conn, sql, stuName);
    }

    @Override
    public List<SelectedCourse> getSelectedCourseByCourse(Connection conn, String course) {
        String sql = "select id, stu_num stuNum, stu_name stuName, course from selected_course where course = ?";
        return getMultiQuery(conn, sql, course);
    }

    @Override
    public List<SelectedCourse> getAll(Connection conn) {
        String sql = "select id, stu_num stuNum, stu_name stuName, course from selected_course";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from selected_course";
        return getValue(conn, sql);
    }
}
