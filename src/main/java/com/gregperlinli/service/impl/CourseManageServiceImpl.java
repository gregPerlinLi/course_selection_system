package com.gregperlinli.service.impl;

import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.dao.impl.CourseDaoImpl;
import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * <code>CourseManageService</code>的实现类
 *
 * @author gregperlinli
 * @see CourseManageService
 * @since 2021-7-28
 */
public class CourseManageServiceImpl implements CourseManageService {
    final private CourseDao courseDao = new CourseDaoImpl();
    final private SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
    private Connection conn = null;

    @Override
    public boolean addCourse(Course course) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( courseDao.getCourseByCourseName(conn, course.getCourseName()) == null) {
                courseDao.insert(conn, course);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Course currentCourse = courseDao.getCourseById(conn, course.getId());
            if ( currentCourse != null) {
                // 先修改该课程下所有已选课程信息
                // selectedCourseDao.deleteByCourse(conn, currentCourse.getCourseName());
                List<SelectedCourse> selectedCourses = selectedCourseDao.getSelectedCourseByCourse(conn, currentCourse.getCourseName());
                for ( SelectedCourse selectedCourse : selectedCourses ) {
                    selectedCourse.setCourse(course.getCourseName());
                    selectedCourseDao.updateById(conn, selectedCourse);
                }
                if ( courseDao.getCourseByCourseName(conn, course.getCourseName()) == null || currentCourse.getCourseName().equals(course.getCourseName()) ) {
                    // 然后再修改课程信息
                    courseDao.updateById(conn, course);
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
    public boolean deleteCourse(int id) {
        final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Course currentCourse = courseDao.getCourseById(conn, id);
            if ( currentCourse != null) {
                // 先删除该课程下所有已选课程信息
                selectedCourseDao.deleteByCourse(conn, currentCourse.getCourseName());
                // 然后再删除课程
                courseDao.deleteById(conn, id);
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
    public Course getCourseById(int id) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return courseDao.getCourseById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existCourseName(String courseName) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( courseDao.getCourseByCourseName(conn, courseName) != null ) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Course> getAllCourse() {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return courseDao.getAll(conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SelectedCourse> getAllSelectedCourse() {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return selectedCourseDao.getAll(conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
