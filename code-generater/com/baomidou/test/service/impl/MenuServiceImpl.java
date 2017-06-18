package com.baomidou.test.service.impl;

import com.baomidou.test.entity.Menu;
import com.baomidou.test.mapper.MenuMapper;
import com.baomidou.test.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 多级菜单 服务实现类
 * </p>
 *
 * @author Yanghu
 * @since 2017-06-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
	
}
