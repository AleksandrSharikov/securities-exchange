package com.exchangeinformant.feed.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should get message by id")
    void shouldGetCard() throws Exception {
        mockMvc.perform(get("/feed/{id}/{rank}", 5, 0))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
