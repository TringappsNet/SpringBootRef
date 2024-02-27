package com.SpringBoot.springbootRef.Registration;

import com.SpringBoot.springbootRef.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegisterService registerService;

    @Test
    public void testGetRegisters() throws Exception {
        mockMvc.perform(get("/api/v1/registers"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteRegister() throws Exception {
        Integer id = 2603;
        when(registerService.deleteRegisterById(id)).thenReturn(false);
        mockMvc.perform(delete("/api/v1/registers/{register_id}", id))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testEditRegister() throws Exception {
        Integer id = 1;
        RegisterRequest request = new RegisterRequest(id, "Anand", "123", "88838", "anand@mail.com", "Nil", "Junit.csv", "yes");
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        when(registerService.editRegisterById(id, request)).thenReturn(true);

        mockMvc.perform(put("/api/v1/registers/{register_id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated"));
    }
    @Test
    public void testSaveRegister_Success() throws Exception {
        String expectedResponse = "{\"status\": 201, \"body\": \"Register created successfully\"}";
        when(registerService.addRegister(any(RegisterRequest.class))).thenReturn(expectedResponse);

        RegisterRequest request = new RegisterRequest(2603,"Anand", "123", "88838", "anand@mail.com", "Nil", "Junit.csv", "yes");
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/api/v1/registers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse));
    }
}


