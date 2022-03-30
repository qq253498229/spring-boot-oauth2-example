package com.example.user;

import com.example.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public List<UserVO> userList() {
        return userMapper.selectAllFetchDetail(null);
    }

    public UserVO showUserDetail(Integer id) {
        return userMapper.selectAllFetchDetail(id).get(0);
    }
}
