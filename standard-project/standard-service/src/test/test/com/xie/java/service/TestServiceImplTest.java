package test.com.xie.java.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.entity.TestBean;
import com.xie.java.service.TestService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * TestServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 14, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestServiceImplTest {


    @Autowired
    TestService testService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getVersion()
     */
    @Test
    public void testGetVersion() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getNameById(Integer id)
     */
    @Test
    public void testGetNameById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getEnviroment()
     */
    @Test
    public void testGetEnviroment() throws Exception {
        List<TestBean> testBeans = testService.queryListBypage(2, 10, null);
        logger.debug(""+testBeans);
    }

    @Test
    public void selectTestPage() throws Exception {
        Page<TestBean> page  = new Page<TestBean>(0,5);
        Page<TestBean> testBeanPage = testService.selectTestPage(page, 0);
        logger.debug(""+testBeanPage);

    }


    @Test
    public void addTestBean() throws Exception {
        TestBean testBean = new TestBean();
        testBean.setName("测233432");
        boolean insert = testService.insert(testBean);
        Assert.assertTrue(insert);
    }


    CountDownLatch countDownLatch = new CountDownLatch(10);


} 
