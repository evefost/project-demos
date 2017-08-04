package com.xie;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.vo.Descript;
import com.xie.vo.User;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@Controller
@RequestMapping()
public class OtherController {
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Descript("这是一个泛型基本数组")
    @RequestMapping(value = "base/list")
    public ResponseDataVo baseList(List<Long> longList) {
        logger.debug("formAdd{}",longList);
        //user.getSchool().toString();
        return ResponseDataVo.success(longList);
    }

    @Descript("这是一个泛型自定义对象数组")
    @RequestMapping(value = "base/list")
    public ResponseDataVo customGic(List<User> userList) {
        logger.debug("formAdd{}",userList);
        //user.getSchool().toString();
        return ResponseDataVo.success(userList);
    }

    @PostMapping(value = "annotation/formAdd")
    public ResponseDataVo annatation(@RequestParam("xieyang") @RequestBody User user,@RequestParam("username")String name) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "other/postBody", method = RequestMethod.POST)
    public ResponseDataVo postBody(@RequestBody User user) {
        logger.debug("postBody{}",user);
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "/other/{id}/{name}", method = RequestMethod.PUT)
    public ResponseDataVo updateUser(@PathVariable Long id, @PathVariable String name) {
        logger.info("更新用户:" + id + "==" + name);
        return ResponseDataVo.success(null, "更新用户");
    }
}
