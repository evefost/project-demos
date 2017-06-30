package test.com.xie.java.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.entity.Tb1;
import com.xie.java.mapper.Tb1Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*"})
public class TestMybatisPlus {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Tb1Mapper testMapper;

    @Test
    public void like() {
        EntityWrapper<Tb1> ew = new EntityWrapper<Tb1>();
        ew.like("name","bb");
        ew.like("description","qq");
        List<Tb1> testBeans = testMapper.selectList(ew);
        logger.debug("size:"+testBeans.size());

    }


    @Test
    public void or() {
        EntityWrapper<Tb1> ew = new EntityWrapper<Tb1>();
        ew.or("name = ? ","bb");
       // ew.like("description","qq");
        List<Tb1> testBeans = testMapper.selectList(ew);
        logger.debug("size:"+testBeans.size());

    }

    //非分页插件分页
    @Test
    public void selectPage() {
        logger.debug("======================================");
        EntityWrapper<Tb1> ew = new EntityWrapper<Tb1>();

        List<Tb1> userList = testMapper.selectPage( new Page<Tb1>(1, 5), ew);
        logger.debug("size:"+userList);
        logger.debug("======================================"+userList);

    }


}
