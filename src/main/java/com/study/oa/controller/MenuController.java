package com.study.oa.controller;

import com.study.oa.po.Menu;
import com.study.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Menu> list() {
        List<Menu> list = menuService.findAll();
        return list;
    }
}
