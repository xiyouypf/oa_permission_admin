package com.study.oa.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String loginName;

    private String password;

    private String nickName;

    private Integer age;

    private String location;

    private String role;

    private static final long serialVersionUID = 1L;

    private List<Role> roleList;

    private List<Permission> permissionList;

}