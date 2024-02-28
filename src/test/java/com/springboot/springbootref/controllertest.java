package com.springboot.springbootref;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springbootref.registration.RegisterRequest;
import com.springboot.springbootref.registration.RegisterService;
import com.springboot.springbootref.registration.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class controllertest {

    @InjectMocks
    private Controller controller;

    @Mock
    private RegisterService registerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisters() {
        List<Register> registers = mock(List.class);
        when(registerService.registers()).thenReturn(registers);

        List<Register> result = controller.registers();

        assertEquals(registers, result);
        verify(registerService, times(1)).registers();
    }

    @Test
    public void testRegister() {
        Register register = mock(Register.class);
        when(registerService.register(1)).thenReturn(Optional.of(register));

        Optional<Register> result = controller.register(1);

        assertEquals(Optional.of(register), result);
        verify(registerService, times(1)).register(1);
    }

    @Test
    public void testSaveRegisterSuccess() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes");
        String result = "{\"status\": 200}";
        when(registerService.addRegister(registerRequest)).thenReturn(result);

        ObjectMapper objectMapper = new ObjectMapper();
        int status;
        JsonNode jsonNode = objectMapper.readTree(result);
        status = jsonNode.get("status").asInt();

        ResponseEntity<String> responseEntity = controller.saveRegister(registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(status, responseEntity.getStatusCodeValue());
        verify(registerService, times(1)).addRegister(registerRequest);
    }


    @Test
    public void testDeleteRegister() {
        Integer Id = 1;
        boolean success = true;
        when(registerService.deleteRegisterById(Id)).thenReturn(success);

        ResponseEntity<String> responseEntity = controller.deleteRegister(Id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted", responseEntity.getBody());
        verify(registerService, times(1)).deleteRegisterById(Id);
    }



    @Test
    public void testEditRegister() {
        Integer Id = 1;
        RegisterRequest registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes");
        boolean success = true;
        when(registerService.editRegisterById(Id, registerRequest)).thenReturn(success);

        ResponseEntity<String> responseEntity = controller.editRegister(Id, registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated", responseEntity.getBody());
        verify(registerService, times(1)).editRegisterById(Id, registerRequest);
    }


}