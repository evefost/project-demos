package com.xie.web.controller;


import com.xie.java.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api("dubbo 测试接口")
@RestController
@RequestMapping("dubbo")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "version", method = RequestMethod.GET)
    public String getVersion() {
        logger.debug("getVersion");
        logger.info("cinfo");
        logger.error("cerror{}", "aaa=============");
        return testService.getVersion();
    }


    @RequestMapping(value = "enviroment", method = RequestMethod.GET)
    @ApiOperation(value = "接口说明(测试)", httpMethod = "GET", notes = "在没有会话、没有签名的情况下，进入方法体")
    public String queryCurrentEnviroment() {
        String enviroment = testService.getEnviroment();
        logger.info("evn:{}", enviroment);
        logger.error("env error{}", enviroment);
        return enviroment;
    }
}
