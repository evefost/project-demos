package com.xie;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.vo.User;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@Controller
@RequestMapping()
public class OtherController {
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "other/formAdd")
    public ResponseDataVo add(User user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "other/postBody", method = RequestMethod.POST)
    public ResponseDataVo postBody(@RequestBody User user) {
        logger.debug("postBody{}",user);
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "other/getRequest", method = RequestMethod.GET)
    public ResponseDataVo getRequest( User user,@RequestParam(required = true)String name) {
        logger.debug("getRequest{}",user);
        return ResponseDataVo.success(user);
    }
}
