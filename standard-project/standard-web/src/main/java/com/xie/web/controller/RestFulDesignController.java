package com.xie.web.controller;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.entity.Tb1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * restful风格接口
 */
@Api(description = "restful风格接口")
@RestController
@RequestMapping("/restful")
public class RestFulDesignController {
    private static final Logger logger = LoggerFactory.getLogger(TbController.class);


    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseDataVo addUser() {
        logger.info("添加用户");
        Tb1 testBean = new Tb1();
        testBean.setId(new Random().nextInt());
        testBean.setName("xieyang");
        return ResponseDataVo.success(testBean, "添加用户");
    }

    //http://127.0.0.1:8080/restful/user/11
    @ApiOperation(value = "获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseDataVo getUser(@PathVariable Long id) {
        logger.info("获取用户id:" + id);

        return ResponseDataVo.success(null, "获取用户");
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseDataVo deleteUser(@PathVariable Long id) {
        logger.info("删除用户id:" + id);

        return ResponseDataVo.success(null, "删除用户");
    }

    @ApiOperation(value = "更新用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")})
    @RequestMapping(value = "/user/{id}/{name}", method = RequestMethod.PUT)
    public ResponseDataVo updateUser(@PathVariable Long id, @PathVariable String name) {
        logger.info("更新用户:" + id + "==" + name);
        return ResponseDataVo.success(null, "更新用户");
    }

}
