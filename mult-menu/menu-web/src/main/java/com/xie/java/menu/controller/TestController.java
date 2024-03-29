package com.xie.java.menu.controller;



import com.xie.java.menu.entity.TestBean;
import com.xie.java.menu.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "serviceVersion", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion() {

        logger.debug("cdebug");
        logger.info("cinfo");
        logger.error("cerror");
        return testService.getVersion();
    }


    @RequestMapping(value = "getEnviroment", method = RequestMethod.GET)
    @ResponseBody
    public String queryCurrentEnviroment() {
        String enviroment = testService.getEnviroment();
        logger.info("evn:{}", enviroment);
        logger.error("env error{}", enviroment);
        return enviroment;
    }

    @RequestMapping(value = "getBean", method = RequestMethod.GET)
    @ResponseBody
    public TestBean getBean() {
        TestBean t = new TestBean();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }
}
