package com.xie.java.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xie.java.entity.TestBean;

import java.util.List;

public interface TestService extends IService<TestBean> {

    String getVersion();

    TestBean getNameById(Integer id);

    String getEnviroment();

    List<TestBean> queryListBypage(Integer page,Integer size,TestBean condition);


    Page<TestBean> selectTestPage(Page<TestBean> page, Integer status);
}
