package com.xie.java.menu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xie.java.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *使用baomiduo自动注入
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectMenuList(@Param("parentId") Integer parentId);
}
