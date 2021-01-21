package com.study.oa.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String name;

    private List<Permission> permissions;

    private static final long serialVersionUID = 1L;
}