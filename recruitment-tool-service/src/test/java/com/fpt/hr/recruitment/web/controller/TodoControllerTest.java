package com.fpt.hr.recruitment.web.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fpt.hr.recruitment.config.WebConfig;
import com.fpt.hr.recruitment.persistence.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class TodoControllerTest {	
	
	private MockMvc mockMvc;
	private static final MediaType UTF_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
    @Autowired
    private TodoService todoServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(todoServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
	public void findById_TodoEntry_ShouldReturnFound() throws Exception{
		mockMvc.perform(get("/todo/find/{id}", ""))
        .andExpect(status().isOk())
        .andExpect(content().contentType(UTF_JSON))
        .andExpect(jsonPath("$.id", is("")))
        .andExpect(jsonPath("$.name", is("")));       
		
		Mockito.verify(todoServiceMock).findById("");
	}
    
}
