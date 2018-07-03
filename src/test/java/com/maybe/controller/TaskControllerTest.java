package com.maybe.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml"})
@WebAppConfiguration
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void list() throws Exception {
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/task/list"));
        actions.andExpect(status().isOk());  //测试请求反馈状态
        String responseString = actions.andReturn().getResponse().getContentAsString();
        System.out.println("--------返回的内容 = " + responseString);
//        actions.andExpect(MockMvcResultMatchers.content().json(CacheUserController.SUCCESS)); //断言返回json数据
    }

    @Test
    public void edit() {
    }

    @Test
    public void del() {
    }

    @Test
    public void add() {
    }

}