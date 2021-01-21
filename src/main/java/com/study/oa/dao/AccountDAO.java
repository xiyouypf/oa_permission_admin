package com.study.oa.dao;

import com.study.oa.po.AccountExample;
import com.study.oa.po.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AccountDAO继承基类
 */
@Mapper
@Repository
public interface AccountDAO extends MyBatisBaseDao<Account, Integer, AccountExample> {
    Account findByLoginNameAndPassword(String loginName, String password);
}
