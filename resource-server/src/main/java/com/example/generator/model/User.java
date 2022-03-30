package com.example.generator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_user
 * 表注释：用户表
 */
@Getter
@Setter
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码，根据需求加密后存储
     */
    @Column(name = "password")
    private String password;

    /**
     * 是否可用，1为可用，0为不可用，默认1可用
     */
    @Column(name = "enable")
    private Boolean enable;
}