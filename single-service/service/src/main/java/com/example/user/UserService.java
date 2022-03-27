package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service
public class UserService implements UserDetailsService {
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    public List<UserVO> findAllFetchRoleAndResource() {
        return userMapper.findAllFetchRoleAndResource(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findAllFetchRoleAndResource(username).get(0);
    }

    public void register(UserRegisterVO userRegisterVO) {
        User user = new User();
        user.setUsername(userRegisterVO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterVO.getPassword()));
        userMapper.insertSelective(user);
        assertNotNull(user.getId());
        userMapper.initRoleByUserId(user.getId());
    }
}
