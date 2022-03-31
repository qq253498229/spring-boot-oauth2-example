package com.example.user;

import cn.hutool.json.JSONUtil;
import com.example.config.TestResourceConfig;
import com.example.user.vo.UserDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 由于sql中使用了 <b>on duplicate key update<b/> 语句，
 * h2数据库不支持，需要在配置中加上 <b>MODE=MySQL<b/>；
 * <br/>
 * 而加上之后可能会影响其它测试用例，所以就提取到单独的一个测试类中
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"unit-test", "unit-test-mysql-mode"})
@Import(TestResourceConfig.class)
public class UserProcessByMySQLModeTest {
    @Resource
    MockMvc mockMvc;

    /**
     * {@link UserController#updatePersonalDetail(UserDetailVO)}
     */
    @Test
    @WithMockUser(authorities = {"updatePersonalDetail", "showPersonalDetail"})
    void updatePersonalDetail() throws Exception {
        String name = "测试用户111";
        Integer age = 22;
        String email = "test111@test.com";
        Integer gender = 2;
        UserDetailVO vo = new UserDetailVO();
        vo.setUserId(1);
        vo.setName(name);
        vo.setAge(age);
        vo.setEmail(email);
        vo.setGender(gender);
        mockMvc.perform(post("/user/updatePersonalDetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(vo))
        ).andExpect(status().isOk())
        ;
        mockMvc.perform(get("/user/showPersonalDetail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.gender").value(gender))
        ;

    }
}
