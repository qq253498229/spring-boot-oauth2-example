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
}