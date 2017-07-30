package com.xie.vo;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/7/29.
 */
public class User {

    @ParamDes(required=true,descript = "主键id")
    private Long id;

    @ParamDes(required=false,descript = "姓名")
    private String name;

    @ParamDes(descript = "年龄")
    private int age;

    private String school;

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
