package com.xie.java.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xie.java.constant.AppConfig;
import com.xie.java.entity.Tb1;
import com.xie.java.mapper.Tb1Mapper;
import com.xie.java.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chargerlink on 2016/11/25.
 */
@Service("testService")
public class TestServiceImpl extends ServiceImpl<Tb1Mapper, Tb1> implements TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Tb1Mapper tb1Mapper;


    @Autowired
    private AppConfig appConfig;

    @Transactional
    public Boolean add(Tb1 testBean) {
        logger.debug("add已调用实现");
        tb1Mapper.insert(testBean);
        logger.debug("add返回");
        return true;
    }

    public String getVersion() {
        logger.debug("sdebug");
        logger.info("sinfo");
        logger.error("serror");
        return "1.0";
    }


    public Tb1 getNameById(Integer id) {
        return tb1Mapper.selectById(id);

    }

    public String getEnviroment() {
        logger.debug("getEnviroment");
        Tb1 t = tb1Mapper.selectById(1);
        String env = appConfig.getPackageEnviroment() + "==" + t.getName();
        return env;
    }

    public List<Tb1> queryListBypage(Integer page, Integer size, Tb1 condition) {
        EntityWrapper<Tb1> ew = new EntityWrapper<Tb1>();

        List<Tb1> userList = tb1Mapper.selectPage( new Page<Tb1>(page, size), ew);
        return userList;
    }

    public Page<Tb1> selectTestPage(Page<Tb1> page, Integer status) {
        page.setRecords(tb1Mapper.selectTestBeans(page, status));
        return page;
    }


}
