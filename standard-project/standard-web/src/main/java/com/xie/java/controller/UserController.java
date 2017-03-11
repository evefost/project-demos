package com.xie.java.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {  
  

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public String  getUserList() {
        return "";
    }  
  

  

  
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")  
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")  
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)  
    public String deleteUser(@PathVariable Long id) {
        return "success";
    }  
  
}  