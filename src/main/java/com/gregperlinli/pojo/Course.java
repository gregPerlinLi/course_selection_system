package com.gregperlinli.pojo;

import java.sql.Date;
import java.sql.Time;

/**
 * 用于对应数据库中的course表
 *
 * @author gregperlinli
 */
public class Course {
    private int id;
    private String courseName;
    private Date startDate;
    private Time startTime;
    private int maxStu;
    private int currentStu = 0;

    public Course() {
    }

    public Course(int id, String courseName, Date startDate, Time startTime, int maxStu) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.maxStu = maxStu;
    }

    public Course(String courseName, Date startDate, Time startTime, int maxStu) {
        this.courseName = courseName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.maxStu = maxStu;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxStu() {
        return maxStu;
    }

    public void setMaxStu(int maxStu) {
        this.maxStu = maxStu;
    }

    public int getCurrentStu() {
        return currentStu;
    }

    public void setCurrentStu(int currentStu) {
        this.currentStu = currentStu;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", maxStu=" + maxStu +
                ", currentStu=" + currentStu +
                '}';
    }
}
