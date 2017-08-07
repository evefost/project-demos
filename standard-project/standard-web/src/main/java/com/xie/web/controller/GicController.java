package com.xie.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.common.response.ResponseDataVo;
import com.xie.vo.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@RestController
@RequestMapping("/api/generparams")
public class GicController {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseDataVo list(GicParams<Aclass,Bclass> user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

    @RequestMapping(value = "formAdds", method = RequestMethod.POST)
    public ResponseDataVo<Page<SimpleUser>> gicParam(ResponseDataVo<SimpleUser> user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }
    @RequestMapping(value = "formAdds222", method = RequestMethod.GET)
    public ResponseDataVo<SimpleUser> gicParam2(SimpleUser user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }

}
