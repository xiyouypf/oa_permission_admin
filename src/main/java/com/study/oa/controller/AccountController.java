package com.study.oa.controller;

import com.github.pagehelper.PageInfo;
import com.study.oa.config.ConfigSystem;
import com.study.oa.po.Account;
import com.study.oa.po.RespStat;
import com.study.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ConfigSystem config;

    @Autowired
    private AccountService accountService;

    //----------------登录-----------------
    @RequestMapping("/login")
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("config", config);
        return "account/login";
    }

    @RequestMapping("/validataAccount")
    @ResponseBody
    public String validataAccount(String loginName, String password, HttpServletRequest request) {
        Account account = accountService.findByLoginNameAndPassword(loginName, password);
        if (account != null) {
            request.getSession().setAttribute("account", account);
            return "success";
        } else {
            return "登录失败";
        }
    }

    //-------------------注册,未完成-----------------
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "account/register";
    }

    @RequestMapping("/validdataLoginName")
    @ResponseBody
    public String validdataLoginName(String loginName) {
        Account account = accountService.findByLoginName(loginName);
        if (account == null) {
            return "success";
        } else {
            return "用户名重复不能注册";
        }
    }

    @PostMapping("/register")
    public String register(Account account, HttpServletRequest request) {
        RespStat respStat = accountService.save(account);
        if (respStat.getCode() == 200) {
            request.getSession().setAttribute("account", account);
            return "index";
        } else {
            return "account/register";
        }
    }

    //-------------------退出登录--------------------
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "index";
    }

    //显示所有用户
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       ModelMap modelMap) {
        PageInfo<Account> page = accountService.findByPage(pageNum, pageSize);
        modelMap.addAttribute("page", page);
        return "account/list";
    }


    //个人资料 未完成
    @RequestMapping("/profile")
    public String profile() {
        return "account/profile";
    }

    //文件上传
    @RequestMapping("/fileUploadController")
    public String fileUploadController(MultipartFile filename, String password, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        try {
            //定位项目的当前路径的
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(), "static/uploads/");
//            System.out.println("upload = " + upload);
//            filename.transferTo(new File(upload + "/" + filename.getOriginalFilename()));
            filename.transferTo(new File("d:/dev/uploads/" + filename.getOriginalFilename()));
            account.setPassword(password);
            account.setLocation(filename.getOriginalFilename());
            RespStat respStat = accountService.update(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "account/profile";
    }

    //根据id删除用户
    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(Integer id) {
        RespStat stat = accountService.deleteById(id);
        return stat;
    }
}
