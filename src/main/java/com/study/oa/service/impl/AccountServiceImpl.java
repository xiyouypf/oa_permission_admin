package com.study.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.oa.dao.AccountDAO;
import com.study.oa.po.Account;
import com.study.oa.po.AccountExample;
import com.study.oa.po.RespStat;
import com.study.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account findByLoginNameAndPassword(String loginName, String password) {
        loginName = loginName.trim();
        password = password.trim();
//        AccountExample example = new AccountExample();
//        example.createCriteria()
//                .andLoginNameEqualTo(loginName)
//                .andPasswordEqualTo(password);
//        List<Account> accounts = accountDAO.selectByExample(example);
//        if (accounts == null || accounts.size() == 0) {
//            return null;
//        } else {
//            return accounts.get(0);
//        }
        Account account = accountDAO.findByLoginNameAndPassword(loginName, password);
        return account;
    }

    @Override
    public PageInfo<Account> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        AccountExample example = new AccountExample();
        List<Account> list = accountDAO.selectByExample(example);
        PageInfo<Account> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    @Override
    public RespStat deleteById(Integer id) {
        //返回影响结果的行数
        int row = accountDAO.deleteByPrimaryKey(id);
        if (row == 1) {
            return RespStat.build(200);
        } else {
            return RespStat.build(500, "删除出错");
        }
    }

    @Override
    public Account findByLoginName(String loginName) {
        loginName = loginName.trim();
        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        List<Account> accounts = accountDAO.selectByExample(example);
        if (accounts == null || accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }

    @Override
    public RespStat save(Account account) {
        //去掉前后空格
        account.setLoginName(account.getLoginName().trim());
        account.setPassword(account.getPassword().trim());
        account.setNickName(account.getNickName().trim());
        account.setLocation(account.getLoginName().trim());
        account.setRole(account.getRole().trim());

        int row = accountDAO.insert(account);
        if (row != 0) {
            return RespStat.build(200, "注册成功");
        } else {
            return RespStat.build(500, "注册失败");
        }
    }

    @Override
    public RespStat update(Account account) {
        //没有的字段不去管,只更新有的字段
        int row = accountDAO.updateByPrimaryKeySelective(account);
        if (row != 0) {
            return RespStat.build(200, "更新成功");
        } else {
            return RespStat.build(200, "更新失败");
        }
    }
}
