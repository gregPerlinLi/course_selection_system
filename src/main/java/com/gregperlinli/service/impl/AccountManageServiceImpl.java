package com.gregperlinli.service.impl;

import com.gregperlinli.dao.AdminDao;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.dao.impl.AdminDaoImpl;
import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * <code>AccountManageService</code>的实现类
 *
 * @author gregperlinli
 * @see AccountManageService
 * @since 2021-7-28
 */
public class AccountManageServiceImpl implements AccountManageService {
    final private StudentDao studentDao = new StudentDaoImpl();
    final private AdminDao adminDao = new AdminDaoImpl();
    private Connection conn = null;

    @Override
    public boolean studentRegist(Student student) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( studentDao.getStuByUsername(conn, student.getUsername()) == null && studentDao.getStuByStuNum(conn, student.getStuNum()) == null ) {
                studentDao.insert(conn, student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean adminRegist(Admin admin) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( adminDao.getAdmByUsername(conn, admin.getUsername()) == null ) {
                adminDao.insert(conn, admin);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean studentUpdate(Student student) {
        final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            // conn.setAutoCommit(false);
            Student currentStudent = studentDao.getStuById(conn, student.getId());
            if ( currentStudent != null ) {
                // 先修改该学生下所有已选课程的信息
                // selectedCourseDao.deleteByStuName(conn, currentStudent.getUsername());
                List<SelectedCourse> selectedCourses = selectedCourseDao.getSelectedCourseByStuName(conn, currentStudent.getUsername());
                for ( SelectedCourse selectedCourse : selectedCourses ) {
                    selectedCourse.setStuName(student.getUsername());
                    selectedCourse.setStuNum(student.getStuNum());
                    selectedCourseDao.updateById(conn, selectedCourse);
                }
                if ( isLegalToUpdate(conn, currentStudent, student) ) {
                    // 然后再修改学生信息
                    studentDao.updateById(conn, student);
                    // 最后提交更改
                    conn.commit();
                    return true;
                } else {
                    // 若存在同名的对象则停止更改并进行回滚
                    conn.rollback();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean adminUpdate(Admin admin) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Admin currentAdmin = adminDao.getAdmById(conn, admin.getId());
            if ( currentAdmin != null ) {
                if ( adminDao.getAdmByUsername(conn, admin.getUsername()) == null || currentAdmin.getUsername().equals(admin.getUsername()) ) {
                    adminDao.updateById(conn, admin);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean studentDelete(int id) {
        final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            // conn.setAutoCommit(false);
            Student currentStudent = studentDao.getStuById(conn, id);
            if ( currentStudent != null ) {
                // 先删除该学生下所有已选课程的信息
                selectedCourseDao.deleteByStuName(conn, currentStudent.getUsername());
                // 然后再删除学生
                studentDao.deleteById(conn, id);
                // 最后提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean adminDelete(int id) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( adminDao.getAdmById(conn, id) != null ) {
                adminDao.deleteById(conn, id);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Student> getAllStudent() {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return studentDao.getAll(conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStuById(int id) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return studentDao.getStuById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existStudentUsername(String username) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( studentDao.getStuByUsername(conn, username) != null ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean existStudentStuNum(String stuNum) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( studentDao.getStuByStuNum(conn, stuNum) != null ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean isLegalToUpdate(Connection conn, Student currentStudent, Student student) {
        return ( ( studentDao.getStuByUsername(conn, student.getUsername()) == null && studentDao.getStuByStuNum(conn, student.getStuNum()) == null ) ||
                (currentStudent.getUsername().equals(student.getUsername()) && (currentStudent.getStuNum().equals(student.getStuNum()))) ||
                ( studentDao.getStuByUsername(conn, student.getUsername()) == null &&  (currentStudent.getStuNum().equals(student.getStuNum()))) ||
                (currentStudent.getUsername().equals(student.getUsername()) && studentDao.getStuByStuNum(conn, student.getStuNum()) == null ) );
    }
}
