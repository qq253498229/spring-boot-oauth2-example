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
        List<UserVO> userVOS = userMapper.selectAllFetchDetail();
        assertEquals(2, userVOS.size());
    }
}