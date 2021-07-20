package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的selected_course表
 *
 * @author gregperlinli
 */
public class SelectedCourse {
    private int id;
    private String stuName;
    private String selectCourse;

    public SelectedCourse() {
    }

    public SelectedCourse(int id, String stuName, String selectCourse) {
        this.id = id;
        this.stuName = stuName;
        this.selectCourse = selectCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSelectCourse() {
        return selectCourse;
    }

    public void setSelectCourse(String selectCourse) {
        this.selectCourse = selectCourse;
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", selectCourse='" + selectCourse + '\'' +
                '}';
    }
}
