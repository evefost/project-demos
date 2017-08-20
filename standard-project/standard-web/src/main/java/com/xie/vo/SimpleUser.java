package com.xie.vo;

import com.xie.java.common.annotation.Descript;
import com.xie.java.common.annotation.Descript2;

import java.io.Serializable;

/**
 * Created by xieyang on 17/8/5.
 */
@Descript(required = true,value = "这时描述")
public class SimpleUser implements Serializable {

    private Long id;

    public Long getId() {
        return id;
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

    private String name;

    private Inner inner;

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    public static class Inner{

       private  String innerName;

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }
    }


}


