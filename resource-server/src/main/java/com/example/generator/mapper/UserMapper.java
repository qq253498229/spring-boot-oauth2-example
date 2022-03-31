package com.example.generator.mapper;

import com.example.generator.model.User;
import com.example.user.vo.UserDetailVO;
import com.example.user.vo.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<UserVO> selectAllFetchDetail(Integer id);

    List<String> showPersonalRole(String username);

    UserDetailVO showPersonalDetail(String username);
}