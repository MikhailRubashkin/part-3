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

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "Lexa")
public class SmokeControllerTest {

    @Autowired
    private SmokeController smokeController;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void smoke () throws Exception{
        this.mockMvc.perform(get("/smoke"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }
    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/smoke"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }

    @Test
    public void smokeEnglish () throws Exception{
        this.mockMvc.perform(get("/smokeEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }
    @Test
    public void messageLisSmokeEnglishtTest() throws Exception {
        this.mockMvc.perform(get("/smokeEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/input").nodeCount(1));
    }

    @Test
    public void mainSmoke () throws Exception{
        this.mockMvc.perform(get("/mainSmoke"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }

    @Test
    public void messageLismainSmokeTest() throws Exception {
        this.mockMvc.perform(get("/mainSmoke"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }

    @Test
    public void mainSmokeEnglish () throws Exception{
        this.mockMvc.perform(get("/mainSmokeEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }
    @Test
    public void messageLismainSmokeEnglishTest() throws Exception {
        this.mockMvc.perform(get("/mainSmokeEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }

    @Test
    public void filter2 () throws Exception{
        this.mockMvc.perform(post ("/filter2").param("filter2", "null"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(4));
    }

    @Test
    public void filter3 () throws Exception{
        this.mockMvc.perform(post ("/filter3").param("filter3", "null"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(4));
    }
}