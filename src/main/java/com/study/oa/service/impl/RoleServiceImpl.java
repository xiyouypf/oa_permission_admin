package com.study.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.oa.dao.RoleDAO;
import com.study.oa.po.Role;
import com.study.oa.po.RoleExample;
import com.study.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public PageInfo<Role> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleDAO.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roles, 5);
        return pageInfo;
    }

    @Override
    public Role findById(int id) {
//        Role role = roleDAO.selectByPrimaryKey(id);
        Role role = roleDAO.findById(id);
        return role;
    }

    @Override
    public void addPermission(int id, int[] permissions) {
//        for (int permission : permissions) {
//            roleDAO.addPermission(id, permission);
//        }
        roleDAO.addPermissions(id, permissions);
    }
}
