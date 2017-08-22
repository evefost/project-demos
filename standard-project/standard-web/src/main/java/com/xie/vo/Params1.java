package com.xie.vo;

import com.xie.java.common.annotation.Descript;

/**
 * Created by xieyang on 17/8/22.
 */
@Descript("测试参数1")
public class Params1 {

    @Descript(required = true,message = "id")
    private Long id;

    @Descript(required = true,message = "姓名")
    private String name;

    @Descript("测试参数1")
    private int age;


    private int level;

    public Long getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setId(Long id) {
        this.id = id;
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
}
