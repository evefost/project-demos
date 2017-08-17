package com.xie.web.controller;

import com.xie.java.common.response.ResponseDataVo;
import com.xie.vo.Aclass;
import com.xie.vo.Bclass;
import com.xie.vo.GicParams;
import com.xie.vo.SimpleUser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/29.
 */
@Api(description = "ajax提交测试")
@RestController
@RequestMapping("/api/generparams")
public class GicController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @RequestMapping(value = "formAdds222", method = RequestMethod.POST)
//    public ResponseDataVo<SimpleUser> gicParam2(SimpleUser user) {
//        logger.debug("formAdd{}",user);
//        //user.getSchool().toString();
//        return ResponseDataVo.success(user);
//    }
//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    public ResponseDataVo list() {
//
//        return null;
//    }

//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    public ResponseDataVo list(String name,Long id) {
//
//        return null;
//    }

//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    public ResponseDataVo list(@RequestBody GicParams<Aclass,Bclass> user) {
//        logger.debug("formAdd{}",user);
//        //user.getSchool().toString();
//        return ResponseDataVo.success(user);
//    }

    @RequestMapping(value = "list1", method = RequestMethod.POST)
    public ResponseDataVo list1(SimpleUser user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }
    @RequestMapping(value = "list2222", method = RequestMethod.POST)
    public ResponseDataVo list2(@RequestBody SimpleUser user) {
        logger.debug("formAdd{}",user);
        //user.getSchool().toString();
        return ResponseDataVo.success(user);
    }
//
//    @RequestMapping(value = "formAdds", method = RequestMethod.POST)
//    public ResponseDataVo<Page<SimpleUser>> gicParam(ResponseDataVo<Page<SimpleUser>> user) {
//        logger.debug("formAdd{}",user);
//        //user.getSchool().toString();
//        return null;
//    }

//    @RequestMapping(value = "formAdds", method = RequestMethod.POST)
//    public ResponseDataVo gicParam(List<Long> user, String name) {
//        logger.debug("formAdd{}", user);
//        //user.getSchool().toString();
//        return null;
//    }


}
