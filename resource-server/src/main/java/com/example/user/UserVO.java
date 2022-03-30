package com.example.user;

import com.example.generator.model.UserDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends UserDetail {
    private String username;
}
