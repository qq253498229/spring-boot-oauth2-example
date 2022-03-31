package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import com.example.user.vo.ResetUserPasswordVO;
import com.example.user.vo.UserDetailVO;
import com.example.user.vo.UserVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    public List<UserVO> userList() {
        return userMapper.selectAllFetchDetail(null);
    }

    public UserVO showUserDetail(Integer id) {
        return userMapper.selectAllFetchDetail(id).get(0);
    }

    public void resetUserPassword(ResetUserPasswordVO resetUserPasswordVO) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", resetUserPasswordVO.getUsername());
        User user = userMapper.selectOneByExample(example);
        user.setPassword(passwordEncoder.encode(resetUserPasswordVO.getPassword()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    public List<String> showPersonalRole(String username) {
        return userMapper.showPersonalRole(username);
    }

    public UserDetailVO showPersonalDetail(String username) {
        return userMapper.showPersonalDetail(username);
    }
}
