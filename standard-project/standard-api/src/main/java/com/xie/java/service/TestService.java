package com.xie.java.service;


import com.xie.java.entity.TestBean;

public interface TestService {

    String getVersion();

    TestBean getNameById(Integer id);

    String getEnviroment();
}
