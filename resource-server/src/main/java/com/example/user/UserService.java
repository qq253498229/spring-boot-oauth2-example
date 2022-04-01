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
        User user = getUserByUsername(resetUserPasswordVO.getUsername());
        user.setPassword(passwordEncoder.encode(resetUserPasswordVO.getPassword()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User getUserByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    public List<String> showPersonalRole(String username) {
        return userMapper.showPersonalRole(username);
    }

    public UserDetailVO showPersonalDetail(String username) {
        return userMapper.showPersonalDetail(username);
    }

    public void updatePersonalDetail(UserDetailVO userDetailVO, String username) {
        User user = getUserByUsername(username);
        userDetailVO.setUserId(user.getId());
        userMapper.updatePersonalDetail(userDetailVO);
    }
}
