package com.gregperlinli.service.impl;

import com.gregperlinli.dao.CourseDao;
import com.gregperlinli.dao.SelectedCourseDao;
import com.gregperlinli.dao.impl.CourseDaoImpl;
import com.gregperlinli.dao.impl.SelectedCourseDaoImpl;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.service.CourseSelectionService;
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
    private Connection conn = null;

    @Override
    public SelectedCourse selectCourse(int id) {
        return null;
    }

    @Override
    public boolean cancelSelection(int id) {
        return false;
    }

    @Override
    public List<Course> queryEnableCourse() {
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
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }
}
