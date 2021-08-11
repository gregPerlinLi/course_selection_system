package com.gregperlinli.service;

import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;

import java.util.List;

/**
 * 用于对学院/年级/班级进行管理
 *
 * @author gregperlinli
 * @since 2021-7-29
 */
public interface CollegeGradeClassManageService {

    /**
     * 新增学院操作
     *
     * @param college 提供需要新增的学院对象
     * @return 返回知否已存在该学院，<code>true</code>则为不存在同名学院，<code>false</code>则为存在同名学院
     */
    boolean collegeInsert(College college);

    /**
     * 新增年级操作
     *
     * @param grade 提供需要新增的年级对象
     * @return 返回知否已存在该年级，<code>true</code>则为不存在同名年级，<code>false</code>则为存在同名年级
     */
    boolean gradeInsert(Grade grade);

    /**
     * 新增班级操作
     *
     * @param classes 提供需要新增的班级对象
     * @return 返回知否已存在该学院，<code>true</code>则为不存在同名班级，<code>false</code>则为存在同名班级
     */
    boolean classInsert(Classes classes);

    /**
     * 修改学院操作<br/>
     * <span style='color:yellow;'>注意：在修改学院的同时会将原来该学院下的所有学生以及班级信息进行修改</span>
     *
     * @param college 提供带有要修改的学院的新的参数的对象 (注意：id属性一定是学院现有的id属性，且不能更改！)
     * @return 返回知否已存在该学院，<code>true</code>则为存在该学院，<code>false</code>则为不存在该学院
     */
    boolean collegeUpdate(College college);

    /**
     * 修改年级操作<br/>
     * <span style='color:yellow;'>注意：在修改年级的同时会将原来该年级下的所有学生以及班级信息进行修改</span>
     *
     * @param grade 提供带有要修改的年级的新的参数的对象 (注意：id属性一定是年级现有的id属性，且不能更改！)
     * @return 返回知否已存在该年级，<code>true</code>则为存在该年级，<code>false</code>则为不存在该年级
     */
    boolean gradeUpdate(Grade grade);

    /**
     * 修改班级操作<br/>
     * <span style='color:yellow;'>注意：在修改班级的同时会将原来该班级下的所有学生信息进行修改</span>
     *
     * @param classes 提供带有要修改的班级的新的参数的对象 (注意：id属性一定是班级现有的id属性，且不能更改！)
     * @return 返回知否已存在该班级，<code>true</code>则为存在该班级，<code>false</code>则为不存在该班级
     */
    boolean classUpdate(Classes classes);

    /**
     * 删除学院操作<br>
     * <span style='color:yellow;'>注意：在删除学院的同时会将原来该学院下的所有学生以及班级信息完全清除，请谨慎使用！</span>
     * @param id 提供要删除的学院id
     * @return 返回知否已存在该学院，<code>true</code>则为存在该学院，<code>false</code>则为不存在该学院
     */
    boolean collegeDelete(int id);

    /**
     * 删除年级操作<br>
     * <span style='color:yellow;'>注意：在删除年级的同时会将原来该年级下的所有学生以及班级信息完全清除，请谨慎使用！</span>
     * @param id 提供要删除的年级id
     * @return 返回知否已存在该年级，<code>true</code>则为存在该年级，<code>false</code>则为不存在该年级
     */
    boolean gradeDelete(int id);

    /**
     * 删除班级操作<br>
     * <span style='color:yellow;'>注意：在删除班级的同时会将原来该班级下的所有学生信息完全清除，请谨慎使用！</span>
     * @param id 提供要删除的班级id
     * @return 返回知否已存在该班级，<code>true</code>则为存在该班级，<code>false</code>则为不存在该班级
     */
    boolean classDelete(int id);

    /**
     * 查找所有学院操作
     *
     * @return 返回带有所有学院对象的集合
     */
    List<College> searchAllCollege();

    /**
     * 查找所有年级操作
     *
     * @return 返回带有所有年级对象的集合
     */
    List<Grade> searchAllGrade();

    /**
     * 根据学院和年级查找班级操作
     *
     * @param college 提供班级的所在学院
     * @param grade 提供班级的所在年级
     * @return 返回带有符合学院和年级条件的班级对象的集合
     */
    List<Classes> searchClassByCollegeAndGrade(String college, String grade);

}
