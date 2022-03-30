package com.example.user;

import com.example.config.MyBatisConfig;
import com.example.generator.mapper.UserMapper;
import com.example.user.bean.UserVO;
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
public class UserMapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    void findAllFetchRoleAndResource() {
        List<UserVO> allFetchRoleAndResource = userMapper.findAllFetchRoleAndResource(null);
        assertEquals(allFetchRoleAndResource.size(), 2);
        assertEquals(allFetchRoleAndResource.get(0).getRoleVOList().size(), 1);
        assertEquals(allFetchRoleAndResource.get(1).getRoleVOList().size(), 2);
        assertEquals(allFetchRoleAndResource.get(0).getAuthorities().size(), 2);
        assertEquals(allFetchRoleAndResource.get(1).getAuthorities().size(), 4);
    }
}
