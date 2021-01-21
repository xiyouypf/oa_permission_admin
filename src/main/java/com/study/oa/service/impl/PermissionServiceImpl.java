package com.study.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.oa.dao.PermissionDAO;
import com.study.oa.po.Permission;
import com.study.oa.po.PermissionExample;
import com.study.oa.po.RespStat;
import com.study.oa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public PageInfo<Permission> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissions = permissionDAO.selectByExample(permissionExample);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions, 5);
        return pageInfo;
    }

    @Override
    public Permission findById(Integer id) {
        Permission permission = permissionDAO.selectByPrimaryKey(id);
        return permission;
    }

    @Override
    public RespStat add(Permission permission) {
        int row = permissionDAO.insert(permission);
        if (row == 1) {
            return RespStat.build(200, "添加权限成功");
        } else {
            return RespStat.build(500, "添加权限失败");
        }
    }

    @Override
    public RespStat update(Permission permission) {
//        PermissionExample example = new PermissionExample();//此方法不可用
//        int row = permissionDAO.updateByExample(permission, example);
        int row = permissionDAO.updateByPrimaryKeySelective(permission);
        if (row == 1) {
            return RespStat.build(200, "权限修改成功");
        } else {
            return RespStat.build(500, "权限修改失败");
        }
    }

    @Override
    public List<Permission> findAll() {
        PermissionExample example = new PermissionExample();
        List<Permission> permissions = permissionDAO.selectByExample(example);
        return permissions;
    }
}
