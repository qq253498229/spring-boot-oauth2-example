package com.example.generator.mapper;

import com.example.generator.model.User;
import com.example.user.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<UserVO> findAllFetchRoleAndResource(String username);

    void initRoleByUserId(Integer id);

    List<String> showPersonalRole(String username);
}