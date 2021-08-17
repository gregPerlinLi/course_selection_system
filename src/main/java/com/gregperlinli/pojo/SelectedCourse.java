package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的selected_course表
 *
 * @author gregperlinli
 * @since 2021-7-20
 */
public class SelectedCourse {
    private int id;
    private String stuNum;
    private String stuName;
    private String course;

    public SelectedCourse() {
    }

    public SelectedCourse(String stuNum, String stuName, String course) {
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.course = course;
    }

    public SelectedCourse(int id, String stuNum, String stuName, String course) {
        this.id = id;
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "id=" + id +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", selectCourse='" + course + '\'' +
                '}';
    }
}
