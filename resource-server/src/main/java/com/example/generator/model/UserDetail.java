package com.example.generator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_user_detail
 * 表注释：用户详情表
 */
@Getter
@Setter
@Table(name = "t_user_detail")
public class UserDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 关联t_user表id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 性别，1为男性，2为女性，3为其它
     */
    @Column(name = "gender")
    private Integer gender;
}