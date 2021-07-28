package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.pojo.Course;

import java.sql.Connection;
import java.util.List;

public class CourseDaoImpl extends BaseDAO<Course> implements CourseDao {
    @Override
    public void insert(Connection conn, Course course) throws Exception {
        String sql = "insert into course(course_name, start_date, start_time, max_stu)values(?,?,?,?)";
        update(conn, sql, course.getCourseName(), course.getStartDate(), course.getStartTime(), course.getMaxStu());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from course where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Course course) throws Exception {
        String sql = "update course set course_name = ?, start_date = ?, start_time = ?, max_stu = ? where id = ?";
        update(conn, sql, course.getCourseName(), course.getStartDate(), course.getStartTime(), course.getMaxStu(), course.getId());
    }

    @Override
    public void updateCurrentStu(Connection conn, int id, int newCurrentStu) throws Exception {
        String sql = "update course set current_stu = ? where id = ?";
        update(conn, sql, newCurrentStu, id);
    }

    @Override
    public Course getCourseByCourseName(Connection conn, String courseName) {
        String sql = "select id, course_name courseName, start_date startDate, start_time startTime, max_stu maxStu, current_stu currentStu from course where course_name = ?";
        return getQuery(conn, sql, courseName);
    }

    @Override
    public List<Course> getAll(Connection conn) {
        String sql = "select id, course_name courseName, start_date startDate, start_time startTime, max_stu maxStu, current_stu currentStu from course";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from course";
        return getValue(conn, sql);
    }
}
