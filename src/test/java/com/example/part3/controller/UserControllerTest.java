package com.example.part3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "Lexa")
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userList () throws Exception{
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }

    @Test
    public void userEditForm () throws Exception{
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }

    @Test
    public void userSave () throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/user")
                .param("username", "Lexa")
                .param("form", "user")
                .param("user_id", "1")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(5))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/span").string("fifth"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/i").string("#new one"));
    }
}