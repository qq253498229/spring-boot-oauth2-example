package com.example.user;

import com.example.generator.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    public List<UserVO> findAllFetchRoleAndResource() {
        return userMapper.findAllFetchRoleAndResource(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findAllFetchRoleAndResource(username).get(0);
    }
}
