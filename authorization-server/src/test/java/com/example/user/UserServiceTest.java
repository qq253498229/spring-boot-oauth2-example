package com.example.user;

import com.example.generator.mapper.UserMapper;
import com.example.user.bean.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserMapper userMapper;

    @Test
    void findAllFetchRoleAndResource() {
        UserVO userVO = new UserVO();
        Mockito.when(userMapper.findAllFetchRoleAndResource(null)).thenReturn(List.of(userVO));

        List<UserVO> users = userService.findAllFetchRoleAndResource();
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals(1, users.size());
    }
}