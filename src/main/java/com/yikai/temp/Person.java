package com.yikai.temp;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/27 17:51
 */
public class Person {
    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
