package com.xie.java.menu.service.impl;

import com.xie.java.menu.constant.AppConfig;
import com.xie.java.menu.dao.TestMapper;
import com.xie.java.menu.entity.TestBean;
import com.xie.java.menu.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chargerlink on 2016/11/25.
 */
@Service("testService")
public class TestServiceImpl implements TestService {
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
        TestBean t = getNameById(2);
        String env = appConfig.getPackageEnviroment() + "==" + t.getName();
        return env;
    }
}
