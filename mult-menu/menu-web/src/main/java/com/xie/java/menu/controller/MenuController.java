package com.xie.java.menu.controller;


import com.xie.java.common.response.ResponseDataVo;
import com.xie.java.menu.entity.Menu;
import com.xie.java.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/api/menus")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "selectMenuList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDataVo selectMenuList(Integer parentId) {
        List<Menu> menus = menuService.selectMenuList(parentId);
        return ResponseDataVo.success(menus);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDataVo add() {
        Menu menu = new Menu();
        menu.setMenuName("测试item");
        menuService.add(menu);
        return ResponseDataVo.success();
    }


}
