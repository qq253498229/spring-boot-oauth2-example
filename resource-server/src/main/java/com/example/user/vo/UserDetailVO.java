package com.example.user.vo;

import lombok.Data;

@Data
public class UserDetailVO {
    private Integer id;
    /**
     * 关联t_user表id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别，1为男性，2为女性，3为其它
     */
    private Integer gender;
}
