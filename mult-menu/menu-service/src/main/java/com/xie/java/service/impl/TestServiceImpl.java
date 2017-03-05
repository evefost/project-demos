package com.xie.java.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.xie.java.constant.AppConfig;
import com.xie.java.dao.TestMapper;
import com.xie.java.entity.TestBean;
import com.xie.java.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chargerlink on 2016/11/25.
 */
@Service("testService")
public class TestServiceImpl extends SuperServiceImpl<TestMapper, TestBean> implements TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestMapper testDao;
    @Autowired
    private AppConfig appConfig;

    public String getVersion() {
        logger.debug("sdebug");
        logger.info("sinfo");
        logger.error("serror");
        return "1.0";
    }


    public TestBean getNameById(Integer id) {
        return testDao.getNameById(id);

    }

    public String getEnviroment() {
        //TestBean t =  testDao.getNameById(1);
        TestBean t = selectById(1l);
        String env = appConfig.getPackageEnviroment() + "==" + t.getName();
        return env;
    }
}
