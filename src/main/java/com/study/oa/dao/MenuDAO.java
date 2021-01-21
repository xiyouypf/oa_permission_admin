package com.study.oa.dao;

import com.study.oa.po.MenuExample;
import com.study.oa.po.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MenuDAO继承基类
 */
@Mapper
@Repository
public interface MenuDAO extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}