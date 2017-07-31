package com.xie.vo;

/**
 * Created by xieyang on 17/7/31.
 */
public class Teacher {

    private String teacherName;

    private String cource;

   private   Student studentBean;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public Student getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(Student studentBean) {
        this.studentBean = studentBean;
    }
}
