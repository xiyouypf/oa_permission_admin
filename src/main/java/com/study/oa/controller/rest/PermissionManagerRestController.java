package com.study.oa.controller.rest;

import com.study.oa.po.Permission;
import com.study.oa.po.RespStat;
import com.study.oa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionManagerRestController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/add")
    public RespStat add(@RequestBody Permission permission) {
        RespStat respStat = null;
        if (permission.getId() == null) {
            respStat = permissionService.add(permission);
        } else {
            respStat = permissionService.update(permission);
        }
        return respStat;
    }

}
