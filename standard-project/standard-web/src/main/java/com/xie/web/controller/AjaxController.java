package com.xie.web.controller;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.entity.Tb1;
import com.xie.vo.ListOrderDistribute;
import com.xie.vo.SimpleUser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@RestController
@RequestMapping("/api/ajax")
public class AjaxController {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseDataVo list(ListOrderDistribute user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }
    @RequestMapping(value = "formAdds", method = RequestMethod.POST)
    public ResponseDataVo gicParam(ResponseDataVo<SimpleUser> user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }
    @RequestMapping(value = "formAdd", method = RequestMethod.POST)
    public ResponseDataVo add(SimpleUser user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "formAdd2", method = RequestMethod.POST)
    public ResponseDataVo<SimpleUser> add(SimpleUser user, String testname) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "postBody", method = RequestMethod.POST)
    public ResponseDataVo postBody(@RequestBody SimpleUser user) {
        logger.debug("postBody{}",user);
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "getRequest", method = RequestMethod.GET)
    public ResponseDataVo getRequest( SimpleUser user,@RequestParam(required = true)String name) {
        logger.debug("getRequest{}",user);
        return ResponseDataVo.success(user);
    }
}
