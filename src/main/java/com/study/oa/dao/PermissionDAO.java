package com.study.oa.dao;

import com.study.oa.po.Permission;
import com.study.oa.po.PermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * PermissionDAO继承基类
 */
@Mapper
@Repository
public interface PermissionDAO extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}