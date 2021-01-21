package com.study.oa.po;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private String name;

    private String roles;

    private static final long serialVersionUID = 1L;
}