package com.xie.java.menu.service;


import com.xie.java.menu.entity.TestBean;

public interface TestService {

    String getVersion();

    TestBean getNameById(Integer id);

    String getEnviroment();
}
