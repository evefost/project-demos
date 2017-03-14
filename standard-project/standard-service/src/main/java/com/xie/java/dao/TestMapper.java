package com.xie.java.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.entity.TestBean;

import java.util.List;


/**
 *使用baomiduo自动注入
 */
public interface TestMapper extends BaseMapper<TestBean> {

    TestBean getNameById(Integer id);

    List<TestBean> selectTestBeans(Page<TestBean> page, Integer status);
}
