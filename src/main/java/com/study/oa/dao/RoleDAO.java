package com.study.oa.dao;

import com.study.oa.po.Role;
import com.study.oa.po.RoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleDAO继承基类
 */
@Mapper
@Repository
public interface RoleDAO extends MyBatisBaseDao<Role, Integer, RoleExample> {
    //一次性发送一组SQL
    void addPermissions(int id, int[] permissions);
    //单条SQL的发送
    void addPermission(int id, int permission);

    Role findById(int id);

}