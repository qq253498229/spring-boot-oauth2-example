package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    public List<UserVO> findAllFetchRoleAndResource() {
        return userMapper.findAllFetchRoleAndResource();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        return UserVO.from(user);
    }
}
