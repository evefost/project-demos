package com.xie.web.controller;

import com.xie.java.common.response.ResponseDataVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public ResponseDataVo login(String username, String password) {

        logger.debug("login:{} {}",username,password);
        return ResponseDataVo.success();
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String getUserList() {
        return "";
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseDataVo deleteUser(@PathVariable Long id) {
        logger.info("删除用户id:" + id);
        return ResponseDataVo.success();
    }

    @RequestMapping("test")
    public Map<String , Object> test(HttpServletRequest request){
        logger.info("==================requestParam:{}" , request.getParameter("name"));
        logger.info("==================sessionAttribute:{}" , request.getSession().getAttribute("name"));
        Map<String , Object> result = new HashMap<String , Object>();
        result.put("returnCode", 123);
        result.put("message", "senvon");
        return result;
    }


}  