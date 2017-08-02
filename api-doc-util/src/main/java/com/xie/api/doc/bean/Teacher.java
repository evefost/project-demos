package com.xie.api.doc.bean;

/**
 * Created by xieyang on 17/7/31.
 */
public class Teacher {

    private String teacherName;

    private short heiht;

    private String cource;

   private   Student studentBean;

    private   Student studentBean22;

    public String getTeacherName() {
        return teacherName;
    }

    public short getHeiht() {
        return heiht;
    }

    public void setHeiht(short heiht) {
        this.heiht = heiht;
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
