package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的student表
 *
 * @author gregperlinli
 */
public class Student {
    private int id;
    private String stuNum;
    private String username;
    private String password;
    private String college;
    private String grade;
    private String stuClass;

    public Student() {
    }

    public Student(int id, String stuNum, String username, String password, String college, String grade, String stuClass) {
        this.id = id;
        this.stuNum = stuNum;
        this.username = username;
        this.password = password;
        this.college = college;
        this.grade = grade;
        this.stuClass = stuClass;
    }

    public Student(String stuNum, String username, String password, String college, String grade, String stuClass) {
        this.stuNum = stuNum;
        this.username = username;
        this.password = password;
        this.college = college;
        this.grade = grade;
        this.stuClass = stuClass;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuNum=" + stuNum +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", college='" + college + '\'' +
                ", grade='" + grade + '\'' +
                ", stuClass='" + stuClass + '\'' +
                '}';
    }
}
