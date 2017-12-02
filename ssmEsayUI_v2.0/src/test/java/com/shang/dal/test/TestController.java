package com.shang.dal.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


/**
 * Springmvc 单元测试类
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/testConfig/test_spring_mvc.xml", "classpath:/testConfig/test_spring_mybatis.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class TestController {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void test_login() throws Exception {
        //创建书架创建的请求
        //请求方式为post
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/login.do");
        
        mockHttpServletRequestBuilder.param("username", "shang");
        mockHttpServletRequestBuilder.param("password", "123");
        mockMvc
        .perform(mockHttpServletRequestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("/main"))
        .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    public void test_menuList() throws Exception {
        //创建书架创建的请求
        //请求方式为post
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/menu/queryByPage.do");
        //有些参数我注释掉了，你可以自行添加相关参数，得到不同的测试结果
        //第一页
        mockHttpServletRequestBuilder.param("page", "1");
        //每页10条记录
        mockHttpServletRequestBuilder.param("rows", "5");
        mockMvc
        .perform(mockHttpServletRequestBuilder)
        .andExpect(status().isOk())
        ;
        //控制台会打印如下结果：
        //MockHttpServletResponse:
        //Status = 200 即为后端成功相应
        //返回数据
    }
}  