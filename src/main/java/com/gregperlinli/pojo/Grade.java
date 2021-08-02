package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的grade表
 *
 * @author gregperlinli
 * @since 2021-7-20
 */
public class Grade {
    private int id;
    private String gradeName;

    public Grade() {
    }

    public Grade(String gradeName) {
        this.gradeName = gradeName;
    }

    public Grade(int id, String gradeName) {
        this.id = id;
        this.gradeName = gradeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
