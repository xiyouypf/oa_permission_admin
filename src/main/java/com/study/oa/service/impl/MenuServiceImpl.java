package com.study.oa.service.impl;

import com.study.oa.po.MenuExample;
import com.study.oa.dao.MenuDAO;
import com.study.oa.po.Menu;
import com.study.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDAO menuDAO;

    public List<Menu> findAll() {
        MenuExample example = new MenuExample();
        List<Menu> list = menuDAO.selectByExample(example);
        return list;
    }
}
