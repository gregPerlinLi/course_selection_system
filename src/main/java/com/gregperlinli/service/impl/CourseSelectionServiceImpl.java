package com.gregperlinli.service.impl;

import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.dao.impl.CourseDaoImpl;
import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.CourseSelectionService;
import com.gregperlinli.utils.FastSearchListUtil;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>CourseSelectionService</code>的实现类
 *
 * @author gregPerlinLi
 * @see com.gregperlinli.service.CourseSelectionService
 * @since 2021-08-16
 */
public class CourseSelectionServiceImpl implements CourseSelectionService {
    private final CourseDao courseDao = new CourseDaoImpl();
    private final SelectedCourseDao selectedCourseDao = new SelectedCourseDaoImpl();
    private static final Integer SELECT_FAILED = 0;
    private static final Integer SELECT_SUCCESS = 1;
    private static final Integer DUPLICATED_SELECT = 2;
    private Connection conn = null;
    private FastSearchListUtil fslu = null;

    @Override
    public Integer selectCourse(int courseId, Student student) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            Course course = courseDao.getCourseById(conn, courseId);
            if ( course.getCurrentStu() < course.getMaxStu() ) {
                List<SelectedCourse> studentSelectedCourses = selectedCourseDao.getSelectedCourseByStuName(conn, student.getUsername());
                fslu = new FastSearchListUtil(studentSelectedCourses, "course");
                List<Object> currentCourseSelected = fslu.searchTasks(course.getCourseName());
                if ( currentCourseSelected.size() > 0 ) {
                    return DUPLICATED_SELECT;
                }
                courseDao.updateCurrentStu( conn, courseId, course.getCurrentStu() + 1 );
                SelectedCourse selectedCourse = new SelectedCourse(student.getStuNum(), student.getUsername(), course.getCourseName());
                selectedCourseDao.insert(conn, selectedCourse );
                return SELECT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return SELECT_FAILED;
    }

    @Override
    public boolean cancelSelection(int selectedCourseId) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            SelectedCourse selectedCourse = selectedCourseDao.getSelectedCourseById(conn, selectedCourseId);
            Course currentCourse = courseDao.getCourseByCourseName(conn, selectedCourse.getCourse());
            if ( currentCourse.getCurrentStu() > 0 ) {
                selectedCourseDao.deleteById(conn, selectedCourseId);
                courseDao.updateCurrentStu(conn, currentCourse.getId(), currentCourse.getCurrentStu() - 1);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Course> queryEnabledCourse() {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            List<Course> courses = courseDao.getAll(conn);
            List<Course> enabledCourses = new ArrayList<>();
            for ( Course course : courses ) {
                Date sqlStartDate = course.getStartDate();
                Time sqlStartTime = course.getStartTime();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localStartDateTime = LocalDateTime.parse(sqlStartDate.toString() + " " + sqlStartTime.toString(), dtf);
                LocalDateTime localCurrentDateTime = LocalDateTime.now();
                if ( localCurrentDateTime.isAfter(localStartDateTime) || localCurrentDateTime.isEqual(localStartDateTime) ) {
                    enabledCourses.add(course);
                }
            }
            return enabledCourses;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SelectedCourse> queryStudentSelectedCourse(String stuName) {
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return selectedCourseDao.getSelectedCourseByStuName(conn, stuName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
