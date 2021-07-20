package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的class表
 *
 * @author gregperlinli
 */
public class Class {
    private int id;
    private String className;
    private String grade;

    public Class() {
    }

    public Class(int id, String className, String grade) {
        this.id = id;
        this.className = className;
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
