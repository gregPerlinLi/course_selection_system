package com.gregperlinli.dao.impl;

import com.gregperlinli.dao.BaseDAO;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.pojo.Student;

import java.sql.Connection;
import java.util.List;

/**
 * @author gregperlinli
 * @see com.gregperlinli.dao.BaseDAO
 * @see com.gregperlinli.dao.StudentDao
 * @since 2021-7-21
 */
public class StudentDaoImpl extends BaseDAO<Student> implements StudentDao {
    @Override
    public void insert(Connection conn, Student student) throws Exception {
       String sql = "insert into student(stu_num, username, password, college, grade, stu_class)values(?,?,?,?,?,?)";
       update(conn, sql, student.getStuNum(), student.getUsername(), student.getPassword(), student.getCollege(), student.getGrade(), student.getStuClass());
    }

    @Override
    public void deleteById(Connection conn, int id) throws Exception {
        String sql = "delete from student where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Student student) throws Exception {
        String sql = "update student set stu_num = ?, username = ?, password = ?, college = ?, grade = ?, stu_class = ? where id = ?";
        update(conn, sql, student.getStuNum(), student.getUsername(), student.getPassword(), student.getCollege(), student.getGrade(), student.getStuClass(), student.getId());
    }

    @Override
    public Student getStuById(Connection conn, int id) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where id = ?";
        return getQuery(conn, sql, id);
    }

    @Override
    public Student getStuByStuNum(Connection conn, String stuNum) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where stu_num = ?";
        return getQuery(conn, sql, stuNum);
    }

    @Override
    public Student getStuByUsername(Connection conn, String username) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where username = ?";
        return getQuery(conn, sql, username);
    }

    @Override
    public List<Student> getStuByCollege(Connection conn, String college) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where college = ?";
        return getMultiQuery(conn, sql, college);
    }

    @Override
    public List<Student> getStuByGrade(Connection conn, String grade) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where grade = ?";
        return getMultiQuery(conn, sql, grade);
    }

    @Override
    public List<Student> getStuByStuClass(Connection conn, String stuClass) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student where stu_class = ?";
        return getMultiQuery(conn, sql, stuClass);
    }

    @Override
    public List<Student> getAll(Connection conn) {
        String sql = "select id, stu_num stuNum, username, password, college, grade, stu_class stuClass from student";
        return getMultiQuery(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from student";
        return getValue(conn, sql);
    }
}
