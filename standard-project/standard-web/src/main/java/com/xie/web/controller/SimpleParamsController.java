package com.xie.web.controller;

import com.xie.java.common.annotation.Descript;
import com.xie.java.common.response.ResponseDataVo;
import com.xie.vo.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@RestController
@RequestMapping("/api/simple")
public class SimpleParamsController {

    private  final Logger logger = LoggerFactory.getLogger(getClass());



    @Descript("简单的get方法参数解释1")
    @RequestMapping(value = "no/params", method = RequestMethod.GET)
    public ResponseDataVo list() {
        //user.getSchool().toString();
        return ResponseDataVo.success(null);
    }
    @Descript("简单的get方法参数解释1")
    @RequestMapping(value = "getParams1", method = RequestMethod.GET)
    public ResponseDataVo list(Aclass aclass,String exxxxx) {
        logger.debug("formAdd{}",aclass);
        //user.getSchool().toString();
        return ResponseDataVo.success(aclass);
    }

    @Descript("简单的get方法参数解释2")
    @RequestMapping(value = "getParams2", method = RequestMethod.GET)
    public ResponseDataVo list(String name, Long id) {
        logger.debug("name:{},id",name,id);
        //user.getSchool().toString();
        return ResponseDataVo.success(name);
    }


    @RequestMapping(value = "generParams", method = RequestMethod.POST)
    public ResponseDataVo gicParam(ResponseDataVo<SimpleUser> user,String exxxxx) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "generParams2", method = RequestMethod.POST)
    public ResponseDataVo gicParam(User user,String exxxxx) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

}
