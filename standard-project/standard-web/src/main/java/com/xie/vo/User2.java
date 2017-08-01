package com.xie.vo;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/7/29.
 */
public class User2 {

    @Descript(required=true,message = "主键id")
    private Long id;

    @Descript(required=false,message = "姓名")
    private String name;

    @Descript(message = "年龄")
    private int age;


    private double aDouble;


    private boolean aBoolean;

    private String school;

    private Teacher teacherBean;

    private Teacher teacherBean2;

    public Teacher getTeacherBean2() {
        return teacherBean2;
    }

    public void setTeacherBean2(Teacher teacherBean2) {
        this.teacherBean2 = teacherBean2;
    }

    public Teacher getTeacherBean() {
        return teacherBean;
    }

    public void setTeacherBean(Teacher teacherBean) {
        this.teacherBean = teacherBean;
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
