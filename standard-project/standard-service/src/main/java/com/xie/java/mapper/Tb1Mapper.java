package com.xie.java.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xie.java.entity.Tb1;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2017-06-18
 */
public interface Tb1Mapper extends BaseMapper<Tb1> {

    Tb1 getNameById(Integer id);

    List<Tb1> selectTestBeans(Page<Tb1> page, Integer status);

}