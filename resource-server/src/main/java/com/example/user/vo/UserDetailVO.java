package com.example.user.vo;

import com.example.user.valid.ValidUserId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDetailVO {
    private Integer id;
    /**
     * 关联t_user表id
     */
    @NotNull(message = "用户id不能为空")
    @ValidUserId
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
