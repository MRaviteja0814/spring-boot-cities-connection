package com.challenge.connection.cities.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class CitiesConnectionControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext applicationContext;


    @BeforeEach
    void setUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();

    }

    @Test
    void cityConnections_connected() throws Exception {

        mockMvc.perform(get("/connected")
                .queryParam("origin", "Boston")
                .queryParam("destination", "New York"))
                .andExpect(status().is2xxSuccessful())
        .andExpect(content().string("yes"));

    }

    @Test
    void cityConnections_not_connected() throws Exception {

        mockMvc.perform(get("/connected")
                .queryParam("origin", "Boston")
                .queryParam("destination", "San Francisco"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("no"));

    }

    @Test
    void cityConnections_transitive_connected() throws Exception {

        mockMvc.perform(get("/connected")
                .queryParam("origin", "Boston")
                .queryParam("destination", "Philadelphia"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("yes"));

    }

    @Test
    void cityConnections_null_cities() throws Exception {

        mockMvc.perform(get("/connected")
                .queryParam("destination", "San Francisco"))
                .andExpect(status().is4xxClientError());
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
    }
}
