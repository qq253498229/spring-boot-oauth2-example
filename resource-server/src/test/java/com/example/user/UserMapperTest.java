package com.example.user;

import com.example.config.MyBatisConfig;
import com.example.generator.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@Import(MyBatisConfig.class)
@ImportAutoConfiguration(MapperAutoConfiguration.class)
@ActiveProfiles("unit-test")
class UserMapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    void selectAllFetchDetail() {
        List<UserVO> userVOS = userMapper.selectAllFetchDetail(null);
        assertEquals(2, userVOS.size());

        List<UserVO> userVOS1 = userMapper.selectAllFetchDetail(1);
        assertEquals(1, userVOS1.size());
        UserVO userVO = userVOS1.get(0);
        assertEquals(1, userVO.getId());
        assertEquals("user", userVO.getUsername());
        assertEquals(1, userVO.getUserId());
        assertEquals("测试用户", userVO.getName());
        assertEquals(12, userVO.getAge());
        assertEquals("test@test.com", userVO.getEmail());
        assertEquals(1, userVO.getGender());
    }
}