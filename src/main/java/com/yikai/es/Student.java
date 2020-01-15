package com.yikai.es;

import java.util.Date;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/1/15 14:16
 */
public class Student {
    private String message;
    private Date date;
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String message, Date date, String name) {
        this.message = message;
        this.date = date;
        this.name = name;
    }
}
