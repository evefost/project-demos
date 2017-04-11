package com.xie.java.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xie.java.constant.AppConfig;
import com.xie.java.dao.TestMapper;
import com.xie.java.entity.TestBean;
import com.xie.java.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chargerlink on 2016/11/25.
 */
@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestMapper, TestBean> implements TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private AppConfig appConfig;

    public String getVersion() {
        logger.debug("sdebug");
        logger.info("sinfo");
        logger.error("serror");
        return "1.0";
    }


    public TestBean getNameById(Integer id) {
        return testMapper.getNameById(id);

    }

    public String getEnviroment() {
        logger.debug("getEnviroment");
        TestBean t = testMapper.selectById(1);
        String env = appConfig.getPackageEnviroment() + "==" + t.getName();
        return env;
    }

    public List<TestBean> queryListBypage(Integer page, Integer size, TestBean condition) {
        EntityWrapper<TestBean> ew = new EntityWrapper<TestBean>();

        List<TestBean> userList = testMapper.selectPage( new Page<TestBean>(page, size), ew);
        return userList;
    }

    public Page<TestBean> selectTestPage(Page<TestBean> page, Integer status) {
        page.setRecords(testMapper.selectTestBeans(page, status));
        return page;
    }


}
