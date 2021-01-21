package com.study.oa.controller;

import com.github.pagehelper.PageInfo;
import com.study.oa.po.Account;
import com.study.oa.po.Permission;
import com.study.oa.po.Role;
import com.study.oa.service.AccountService;
import com.study.oa.service.PermissionService;
import com.study.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    //账户管理
    @RequestMapping("/accountList")
    public String accountList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              ModelMap modelMap) {
        PageInfo<Account> page = accountService.findByPage(pageNum, pageSize);
        modelMap.addAttribute("page", page);
        return "manager/accountList";
    }

    //角色管理
    @RequestMapping("/roleList")
    public String roleList(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5") Integer pageSize,
                           ModelMap modelMap) {
        PageInfo<Role> page = roleService.findByPage(pageNum, pageSize);
        modelMap.addAttribute("page", page);
        return "manager/roleList";
    }

    //权限管理
    @RequestMapping("/permissionList")
    public String permissionList(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 ModelMap modelMap) {
        PageInfo<Permission> page = permissionService.findByPage(pageNum, pageSize);
        modelMap.addAttribute("page", page);
        return "manager/permissionList";
    }

    //权限修改
    @RequestMapping("/permissionModify")
    public String permissionModify(Integer id, ModelMap modelMap) {
        Permission permission = permissionService.findById(id);
        modelMap.addAttribute("p", permission);
        return "manager/permissionModify";
    }

    //添加权限
    @RequestMapping("/permissionAdd")
    public String permissionAdd() {
        return "manager/permissionModify";
    }

    //角色权限
    @RequestMapping("/rolePermission/{id}")
    public String rolePermission(@PathVariable int id, ModelMap modelMap, HttpServletRequest request) {
        Role role = roleService.findById(id);
        System.out.println("role = " + role);
        List<Permission> pList = permissionService.findAll();
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("pList", pList);
        return "manager/rolePermission";
    }
}
