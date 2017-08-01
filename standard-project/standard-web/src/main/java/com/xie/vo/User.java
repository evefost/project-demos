package com.xie.vo;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/29.
 */
public class User {


    private String school;

    private List<Teacher> teacherList;

    private Set<Student> students;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
