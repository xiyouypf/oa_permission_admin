package com.study.oa.filter;

import com.study.oa.po.Account;
import com.study.oa.po.Permission;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户权限处理
 */
@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {
    private final String[] IGNORE_URI = {"/index", "/account/validataAccount",
            "/css/", "/js/", "/account/login", "/account/register", "/errorPage",
            "/images", "/account/toRegister", "/account/validdataLoginName"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //用处: 可以加在Filter启动之前需要的资源
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        // 当前访问的URI是不是 在Ignore列表里
        System.out.println("uri:" + uri);
        boolean pass = canPassIgnore(uri);
        if (pass) {
            // 在的话 放行
            chain.doFilter(request, response);
            return;
        }
        // 是否已登录，从session里找 Account
        Account account = (Account) request.getSession().getAttribute("account");
        System.out.println("getSession account:" + account);
        if (null == account) {
            // 没登录 跳转登录页面
            response.sendRedirect("/account/login");
            return;
        }
        // 已登录用户是否有权限访问当前页面
        if (!hasAuth(account.getPermissionList(), uri)) {
            request.setAttribute("msg", "您无权访问当前页面:" + uri);
            request.getRequestDispatcher("/errorPage").forward(request, response);
            return;
        }
        System.out.println("----filter----" + uri);
        chain.doFilter(request, response);
    }

    //是否有权限访问某个URI
    private boolean hasAuth(List<Permission> permissionList, String uri) {
        for (Permission permission : permissionList) {
            if (uri.startsWith(permission.getUri())) {
                return true;
            }
        }
        return false;
    }

    //是否过滤
    private boolean canPassIgnore(String uri) {
        //判断 访问的URI的起始部分 是否包含Ignore
        //这样下级目录也能访问了
        for (String val : IGNORE_URI) {
            if (uri.startsWith(val)) {
                return true;
            }
        }
        return false;
    }
}
