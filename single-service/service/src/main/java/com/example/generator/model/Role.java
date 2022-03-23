package com.example.generator.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 表名：t_role
 * 表注释：角色表
*/
@Getter
@Setter
@Table(name = "`t_role`")
public class Role {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "`description`")
    private String description;
}