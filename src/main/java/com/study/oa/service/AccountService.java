package com.study.oa.service;

import com.github.pagehelper.PageInfo;
import com.study.oa.po.Account;
import com.study.oa.po.RespStat;

import java.util.List;

public interface AccountService {

    Account findByLoginNameAndPassword(String loginName, String password);

    PageInfo<Account> findByPage(int pageNum, int pageSize);

    RespStat deleteById(Integer id);

    Account findByLoginName(String loginName);

    RespStat save(Account account);

    RespStat update(Account account);
}
