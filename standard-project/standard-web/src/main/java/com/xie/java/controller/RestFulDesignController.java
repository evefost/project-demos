package com.xie.java.controller;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.entity.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * restful风格接口
 */
@Controller
@RequestMapping("/restful")
public class RestFulDesignController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDataVo addUser() {
        logger.info("添加用户");
        TestBean testBean = new TestBean();
        testBean.setId(new Random().nextInt());
        testBean.setName("xieyang");
        return ResponseDataVo.success(testBean, "添加用户");
    }

    //http://127.0.0.1:8080/restful/user/11
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDataVo getUser(@PathVariable Long id) {
        logger.info("获取用户id:" + id);

        return ResponseDataVo.success(null, "获取用户");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDataVo deleteUser(@PathVariable Long id) {
        logger.info("删除用户id:" + id);

        return ResponseDataVo.success(null, "删除用户");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDataVo updateUser(@PathVariable Long id) {
        logger.info("更新用户:" + id);
        return ResponseDataVo.success(null, "更新用户");
    }

}
