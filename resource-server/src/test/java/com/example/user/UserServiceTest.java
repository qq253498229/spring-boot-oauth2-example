package com.example.user;

import com.example.generator.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserMapper userMapper;

    @Test
    void userList() {
        Mockito.when(userMapper.selectAllFetchDetail()).thenReturn(Arrays.asList(new UserVO(), new UserVO()));

        List<UserVO> userVOS = userService.userList();
        assertEquals(2, userVOS.size());
    }
}