package com.gregperlinli.service.impl;

import com.gregperlinli.dao.ClassesDao;
import com.gregperlinli.dao.CollegeDao;
import com.gregperlinli.dao.GradeDao;
import com.gregperlinli.dao.StudentDao;
import com.gregperlinli.dao.impl.ClassesDaoImpl;
import com.gregperlinli.dao.impl.CollegeDaoImpl;
import com.gregperlinli.dao.impl.GradeDaoImpl;
import com.gregperlinli.dao.impl.StudentDaoImpl;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gregperlinli
 * @see CollegeGradeClassManageService
 * @since 2021-7-29
 */
public class CollegeGradeClassManageServiceImpl implements CollegeGradeClassManageService {
    Connection conn = null;

    @Override
    public boolean collegeInsert(College college) {
        final CollegeDao collegeDao = new CollegeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( collegeDao.getCollegeByCollegeName(conn, college.getCollegeName()) == null ) {
                collegeDao.insert(conn, college);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean gradeInsert(Grade grade) {
        final GradeDao gradeDao = new GradeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( gradeDao.getGradeByGradeName(conn, grade.getGradeName()) == null ) {
                gradeDao.insert(conn, grade);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean classInsert(Classes classes) {
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            if ( classesDao.getClassByClassName(conn, classes.getClassesName()) == null ) {
                classesDao.insert(conn, classes);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return false;
    }

    @Override
    public boolean collegeUpdate(College college) {
        final CollegeDao collegeDao = new CollegeDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            College currentCollege = collegeDao.getCollegeById(conn, college.getId());
            if ( currentCollege != null ) {
                //先修改该学院下的所有学生信息和所有的班级信息
                List<Student> students = studentDao.getStuByCollege(conn, currentCollege.getCollegeName());
                for ( Student student : students ) {
                    student.setCollege(college.getCollegeName());
                    studentDao.updateById(conn, student);
                }
                List<Classes> classesList = classesDao.getClassesByCollege(conn, currentCollege.getCollegeName());
                for ( Classes classes : classesList ) {
                    classes.setCollege(college.getCollegeName());
                    classesDao.updateById(conn, classes);
                }
                if ( collegeDao.getCollegeByCollegeName(conn, college.getCollegeName()) == null ) {
                    // 然后再修改学院信息
                    collegeDao.updateById(conn, college);
                    // 最后提交更改
                    conn.commit();
                    return true;
                } else {
                    // 若存在同名对象则停止更改并进行回滚
                    conn.rollback();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean gradeUpdate(Grade grade) {
        final GradeDao gradeDao = new GradeDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Grade currentGrade = gradeDao.getGradeById(conn, grade.getId());
            if ( currentGrade != null ) {
                //先修改该年级下的所有学生信息和所有的班级信息
                List<Student> students = studentDao.getStuByGrade(conn, currentGrade.getGradeName());
                for ( Student student : students ) {
                    student.setGrade(grade.getGradeName());
                    studentDao.updateById(conn, student);
                }
                List<Classes> classesList = classesDao.getClassesByGrade(conn, currentGrade.getGradeName());
                for ( Classes classes : classesList ) {
                    classes.setGrade(grade.getGradeName());
                    classesDao.updateById(conn, classes);
                }
                if ( gradeDao.getGradeByGradeName(conn, grade.getGradeName()) == null ) {
                    // 然后再修改年级信息
                    gradeDao.updateById(conn, grade);
                    // 最后提交更改
                    conn.commit();
                    return true;
                } else {
                    // 若存在同名对象则停止更改并进行回滚
                    conn.rollback();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean classUpdate(Classes classes) {
        final ClassesDao classesDao = new ClassesDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Classes currentClasses = classesDao.getClassById(conn, classes.getId());
            if ( currentClasses != null ) {
                //先修改该班级下的所有学生信息
                List<Student> students = studentDao.getStuByStuClass(conn, currentClasses.getClassesName());
                for ( Student student : students ) {
                    student.setStuClass(classes.getClassesName());
                    studentDao.updateById(conn, student);
                }
                if ( classesDao.getClassByClassName(conn, classes.getClassesName()) == null ) {
                    // 然后再修改班级信息
                    classesDao.updateById(conn, classes);
                    // 最后提交更改
                    conn.commit();
                    return true;
                } else {
                    // 若存在同名对象则停止更改并进行回滚
                    conn.rollback();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean collegeDelete(int id) {
        final CollegeDao collegeDao = new CollegeDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            College currentCollege = collegeDao.getCollegeById(conn, id);
            if ( currentCollege != null ) {
                // 先删除该学院下的所有学生信息和所有的班级信息
                studentDao.deleteByCollege(conn, currentCollege.getCollegeName());
                classesDao.deleteByCollege(conn, currentCollege.getCollegeName());
                // 然后再删除学院
                collegeDao.deleteById(conn, id);
                // 最后提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean gradeDelete(int id) {
        final GradeDao gradeDao = new GradeDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Grade currentGrade = gradeDao.getGradeById(conn, id);
            if ( currentGrade != null ) {
                // 先删除该年级下的所有学生信息和所有的班级信息
                studentDao.deleteByGrade(conn, currentGrade.getGradeName());
                classesDao.deleteByGrade(conn, currentGrade.getGradeName());
                // 然后再删除年级
                gradeDao.deleteById(conn, id);
                // 最后提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public boolean classDelete(int id) {
        final ClassesDao classesDao = new ClassesDaoImpl();
        final StudentDao studentDao = new StudentDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            // 关闭自动提交
            conn.setAutoCommit(false);
            Classes currentClass = classesDao.getClassById(conn, id);
            if ( currentClass != null ) {
                // 先删除该班级下的所有学生信息
                studentDao.deleteByClass(conn, currentClass.getClassesName());
                // 然后再删除班级
                classesDao.deleteById(conn, id);
                // 最后提交更改
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 恢复自动提交
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeResource(conn, null);
            }
        }
        return false;
    }

    @Override
    public List<College> searchAllCollege() {
        final CollegeDao collegeDao = new CollegeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return collegeDao.getAll(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }

    @Override
    public List<Grade> searchAllGrade() {
        final GradeDao gradeDao = new GradeDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            return gradeDao.getAll(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }

    @Override
    public List<Classes> searchClassByCollegeAndGrade(String college, String grade) {
        final ClassesDao classesDao = new ClassesDaoImpl();
        try {
            conn = JDBCUtils.getConnectionWithPool();
            List<Classes> classesByCollege = classesDao.getClassesByCollege(conn, college);
            List<Classes> classesByCollegeAndGrade = new ArrayList<>();
            for ( Classes classes : classesByCollege ) {
                if ( grade.equals(classes.getGrade()) ) {
                    classesByCollegeAndGrade.add(classes);
                }
            }
            return classesByCollegeAndGrade;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }
}
