package com.xie.java.test;


import com.xie.java.entity.TestBean;
import com.xie.java.service.TestService;
import com.xie.mode.Mode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api("dsdsdsdsd")
@RestController
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
        logger.error("cerror{}","aaa=============");
        return testService.getVersion();
    }


    @RequestMapping(value = "getEnviroment", method = RequestMethod.GET)
    @ApiOperation(value="接口说明(测试)",httpMethod="GET",notes="在没有会话、没有签名的情况下，进入方法体")
    public String queryCurrentEnviroment() {
        String enviroment = testService.getEnviroment();
        logger.info("evn:{}", enviroment);
        logger.error("env error{}", enviroment);
        return enviroment;
    }

    @RequestMapping(value = "getBean", method = RequestMethod.GET)
    public TestBean getBean() {
        TestBean t = new TestBean();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public TestBean query( Mode bean) {
        TestBean t = new TestBean();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public TestBean postInfo( @RequestBody Mode bean) {
        TestBean t = new TestBean();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }
}
