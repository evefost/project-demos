package com.xie.java.menu.service.impl;

import com.xie.java.menu.dao.MenuMapper;
import com.xie.java.menu.entity.Menu;
import com.xie.java.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("menuService")
public class MenuServiceImpl   implements MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> selectMenuList(Integer menuId) {

        return menuMapper.selectMenuList(menuId);
    }

    public Integer add(Menu menu) {
        return menuMapper.insert(menu);
    }
}
