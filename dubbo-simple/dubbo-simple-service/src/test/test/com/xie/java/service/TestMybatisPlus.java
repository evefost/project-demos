package test.com.xie.java.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.dao.TestMapper;
import com.xie.java.entity.TestBean;
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

    @Autowired
    TestMapper testMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void like() {
        EntityWrapper<TestBean> ew = new EntityWrapper<TestBean>();
        ew.like("name", "bb");
        ew.like("description", "qq");
        List<TestBean> testBeans = testMapper.selectList(ew);
        logger.debug("size:" + testBeans.size());

    }


    @Test
    public void or() {
        EntityWrapper<TestBean> ew = new EntityWrapper<TestBean>();
        ew.or("name = ? ", "bb");
        // ew.like("description","qq");
        List<TestBean> testBeans = testMapper.selectList(ew);
        logger.debug("size:" + testBeans.size());

    }

    //非分页插件分页
    @Test
    public void selectPage() {

        EntityWrapper<TestBean> ew = new EntityWrapper<TestBean>();

        List<TestBean> userList = testMapper.selectPage(new Page<TestBean>(1, 5), ew);
        logger.debug("size:" + userList);

    }


}
