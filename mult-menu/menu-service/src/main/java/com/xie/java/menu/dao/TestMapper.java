package com.xie.java.menu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xie.java.menu.entity.TestBean;

/**
 *使用baomiduo自动注入
 */
public interface TestMapper extends BaseMapper<TestBean> {

    TestBean getNameById(Integer id);
}
