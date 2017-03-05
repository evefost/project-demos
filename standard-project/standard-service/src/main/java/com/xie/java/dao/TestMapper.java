package com.xie.java.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.xie.java.entity.TestBean;


/**
 *使用baomiduo自动注入
 */
public interface TestMapper extends AutoMapper<TestBean> {

    TestBean getNameById(Integer id);
}
