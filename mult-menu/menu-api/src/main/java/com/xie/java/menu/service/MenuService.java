package com.xie.java.menu.service;


import com.xie.java.menu.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> selectMenuList(Integer menuId);


    Boolean add(Menu menu);
}
