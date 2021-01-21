package com.study.oa.service;

import com.github.pagehelper.PageInfo;
import com.study.oa.po.Role;

public interface RoleService {
    PageInfo<Role> findByPage(Integer pageNum, Integer pageSize);

    Role findById(int id);

    void addPermission(int id, int[] permissions);
}
