package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的class表
 *
 * @author gregperlinli
 */
public class Classes {
    private int id;
    private String classesName;
    private String college;
    private String grade;

    public Classes() {
    }

    public Classes(String classesName, String college, String grade) {
        this.classesName = classesName;
        this.college = college;
        this.grade = grade;
    }

    public Classes(int id, String classesName, String college, String grade) {
        this.id = id;
        this.classesName = classesName;
        this.college = college;
        this.grade = grade;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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
        return "Classes{" +
                "id=" + id +
                ", className='" + classesName + '\'' +
                ", college='" + college + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
