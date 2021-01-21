package com.study.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String index1() {
        return "index";
    }

    @RequestMapping("/errorPage")
    public String errorPage(ModelMap modelMap) {
        return "errorPage";
    }
}
