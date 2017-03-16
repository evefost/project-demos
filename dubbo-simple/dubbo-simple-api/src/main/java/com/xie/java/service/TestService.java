package com.xie.java.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.entity.TestBean;

import java.util.List;

public interface TestService {

    String getVersion();

    TestBean getNameById(Integer id);

    String getEnviroment();

    List<TestBean> queryListBypage(Integer page, Integer size, TestBean condition);


    Page<TestBean> selectTestPage(Page<TestBean> page, Integer status);
}
