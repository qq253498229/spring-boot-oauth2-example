package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

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
        List<UserVO> allFetchRoleAndResource = userMapper.findAllFetchRoleAndResource(username);
        if (ObjectUtils.isEmpty(allFetchRoleAndResource)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return allFetchRoleAndResource.get(0);
    }

    public void register(UserRegisterVO userRegisterVO) {
        User user = new User();
        user.setUsername(userRegisterVO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterVO.getPassword()));
        userMapper.insertSelective(user);
        assertNotNull(user.getId());
        userMapper.initRoleByUserId(user.getId());
    }

    public User selectUserByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }
}
