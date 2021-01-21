package com.study.oa.service;

import com.github.pagehelper.PageInfo;
import com.study.oa.po.Permission;
import com.study.oa.po.RespStat;

import java.util.List;

public interface PermissionService {

    PageInfo<Permission> findByPage(Integer pageNum, Integer pageSize);

    Permission findById(Integer id);

    RespStat add(Permission permission);

    RespStat update(Permission permission);

    List<Permission> findAll();
}
