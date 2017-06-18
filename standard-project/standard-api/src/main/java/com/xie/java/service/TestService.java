package com.xie.java.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xie.java.entity.Tb1;

import java.util.List;

public interface TestService extends IService<Tb1> {

    Boolean add(Tb1 testBean);

    String getVersion();

    Tb1 getNameById(Integer id);

    String getEnviroment();

    List<Tb1> queryListBypage(Integer page,Integer size,Tb1 condition);


    Page<Tb1> selectTestPage(Page<Tb1> page, Integer status);
}
