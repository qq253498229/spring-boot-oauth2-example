package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.generator.model.User;
import com.example.user.vo.ResetUserPasswordVO;
import com.example.user.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserMapper userMapper;
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // mapper插件在测试之前要初始化table，否则构建example会失败
        EntityHelper.initEntityNameMap(User.class, new Config());
    }

    @Test
    void userList() {
        Mockito.when(userMapper.selectAllFetchDetail(null)).thenReturn(Arrays.asList(new UserVO(), new UserVO()));
        List<UserVO> userVOS = userService.userList();
        assertEquals(2, userVOS.size());
    }

    @Test
    void showUserDetail() {
        UserVO userVO = new UserVO();
        Mockito.when(userMapper.selectAllFetchDetail(1)).thenReturn(List.of(userVO));
        UserVO result = userService.showUserDetail(1);
        assertEquals(result, userVO);
    }

    @Test
    void resetUserPassword() {
        String username = "username";
        String password = "password";

        String encode = new BCryptPasswordEncoder().encode(password);
        Mockito.when(passwordEncoder.encode(password)).thenReturn(encode);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encode);

        Mockito.when(userMapper.selectOneByExample(any())).thenReturn(user);

        ResetUserPasswordVO vo = new ResetUserPasswordVO();
        vo.setUsername(username);
        vo.setPassword(password);
        userService.resetUserPassword(vo);
    }
}