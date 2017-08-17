package com.xie.vo;

import com.alibaba.fastjson.JSON;
import com.xie.java.common.annotation.Descript;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/29.
 */
@Descript("用户类")
public class User extends BaseSimUser{


    @Descript(required = true,message = "学校名称")
    private String school;

    @Descript("教师数组")
    public List<Teacher> teacherList;

    private Set<Student> students;

    private String tttttt;

    private Student student;

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
