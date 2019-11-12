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
public class MainControllerTest {

    @Autowired
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/form[2]/button").string("Lexa"));
    }

    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }

    @Test
    public void greetingPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/div[1]/h1").string("Lexa"));
    }

    @Test
    public void greetingListTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }
    @Test
    public void greetingEnglishPageTest() throws Exception {
        this.mockMvc.perform(get("/greetingEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/div[1]/h1").string("Lexa"));
    }

    @Test
    public void greetingEnglishListTest() throws Exception {
        this.mockMvc.perform(get("/greetingEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='collapsibleNavbar']/ul/form/input").nodeCount(1));
    }

    @Test
    public void filterMessageTest() throws Exception {
        this.mockMvc.perform(post ("/filter").param("filter", "null"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(4));

    }

    @Test
    public void mainEnglishPageTest() throws Exception {
        this.mockMvc.perform(get("/mainEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/nav/form[2]/button").string("Lexa"));
    }

    @Test
    public void TemperatureListTest() throws Exception {
        this.mockMvc.perform(get("/mainEnglish"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/nav/form[2]/input").nodeCount(1));
    }

    @Test
    public void filterTemperatureTest() throws Exception {
        this.mockMvc.perform(post ("/filter4").param("filter4", "null"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(4));

    }
}