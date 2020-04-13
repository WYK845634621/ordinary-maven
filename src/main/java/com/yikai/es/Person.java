package com.yikai.es;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/1/22 14:58
 */
public class Person {
    private String id;
    private String name;
    private String sex;
    private String remark;


    public Person() {
        String t = Math.random()*10000 +"";
        this.id = t;
        this.name = "this is name : " + t;
        this.sex = Math.random()*3 + "";
        this.remark = "备注";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
