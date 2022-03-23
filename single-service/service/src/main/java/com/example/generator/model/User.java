package com.example.generator.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 表名：t_user
 * 表注释：用户表
*/
@Getter
@Setter
@Table(name = "`t_user`")
public class User {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "`username`")
    private String username;

    /**
     * 密码，根据需求加密后存储
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 是否可用，1为可用，0为不可用，默认1可用
     */
    @Column(name = "`enable`")
    private Boolean enable;
}