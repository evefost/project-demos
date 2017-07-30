package com.xie.web.controller;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.entity.Tb1;
import com.xie.vo.User;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@RestController
@RequestMapping("/api/ajax")
public class AjaxController {
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "formAdd", method = RequestMethod.POST)
    public ResponseDataVo add(User user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "postBody", method = RequestMethod.POST)
    public ResponseDataVo postBody(@RequestBody User user) {
        logger.debug("postBody{}",user);
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "getRequest", method = RequestMethod.GET)
    public ResponseDataVo getRequest( User user,@RequestParam(required = true)String name) {
        logger.debug("getRequest{}",user);
        return ResponseDataVo.success(user);
    }
}
