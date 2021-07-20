package com.gregperlinli.pojo;

/**
 * 用于对应数据库中的college表
 *
 * @author gregperlinli
 */
public class College {
    private int id;
    private String collegeName;

    public College() {
    }

    public College(int id, String collegeName) {
        this.id = id;
        this.collegeName = collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}
