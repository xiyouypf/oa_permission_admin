package com.study.oa.controller.rest;

import com.study.oa.po.RespStat;
import com.study.oa.service.PermissionService;
import com.study.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/manager/role")
public class RoleManagerRestController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/Permission/add")
    public RespStat permissionAdd(@RequestParam int[] permissions,
                                  @RequestParam int id) {
        //insert into role_permission (role_id, permission_id) values (1,1);
        roleService.addPermission(id, permissions);
        System.out.println(Arrays.toString(permissions));
        return RespStat.build(200);
    }
}
