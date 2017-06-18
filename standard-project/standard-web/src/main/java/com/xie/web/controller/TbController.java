package com.xie.web.controller;


import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.entity.Tb1;
import com.xie.java.service.TestService;
import com.xie.mode.Mode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(description = "测试接口")
@RestController
@RequestMapping("/api/test")
public class TbController {
    private static final Logger logger = LoggerFactory.getLogger(TbController.class);

    @Autowired
    private TestService testService;



    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseDataVo add() {
        Tb1 testBean = new Tb1();
        testBean.setName("张三添");
        testService.add(testBean);
        return ResponseDataVo.success();
    }


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
    public Tb1 getBean() {
        Tb1 t = new Tb1();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Tb1 query( Mode bean) {
        Tb1 t = new Tb1();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public Tb1 postInfo( @RequestBody Mode bean) {
        Tb1 t = new Tb1();
        t.setId(12);
        t.setName("abcdef");
        return t;
    }
}
