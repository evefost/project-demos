package com.xie.java.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xieyang on 17/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:*pring-servlet.xml", "classpath:*applicationContext.xml"})
public class BaseTest {
}
