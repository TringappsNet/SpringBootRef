package com.SpringBoot.springbootRef;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRegisters() {

        List<Register> registers = new ArrayList<>();

        when(registerService.getRegisters()).thenReturn(registers);

        List<Register> result = controller.getRegisters();

        assertEquals(registers, result);
        verify(registerService, times(1)).getRegisters();
    }

    @Test
    void testGetRegister() {
        Register register = new Register();
        when(registerService.getRegister(1)).thenReturn(Optional.of(register));

        Optional<Register> result = controller.getRegister(1);

        assertEquals(register, result.get());
        verify(registerService, times(1)).getRegister(1);
    }

    @Test
    void testPostRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(1, "ANAND", "0000", "9384979966", "ANAND@mail.com", "Developer", "csvData", "yes");
        when(registerService.addRegister(any(RegisterRequest.class))).thenReturn("{\"status\": 200}");

        ResponseEntity<String> responseEntity = controller.saveRegister(registerRequest);

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        verify(registerService, times(1)).addRegister(any(RegisterRequest.class));
    }

    @Test
    void testDeleteRegister() {
        Integer userId = 1;
        boolean success = true;
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body("Deleted");
        when(registerService.deleteRegisterById(userId)).thenReturn(success);

        ResponseEntity<String> result = controller.deleteRegister(userId);

        assertEquals(responseEntity, result);
        verify(registerService, times(1)).deleteRegisterById(userId);
    }

    @Test
    void testEditRegister() {
        RegisterRequest registerRequest = new RegisterRequest(1, "ANAND", "dsnn9", "999999999", "ANAND@mail.com", "Developer", "csvData", "yes");
        when(registerService.editRegisterById(1, registerRequest)).thenReturn(true);

        ResponseEntity<String> responseEntity = controller.editRegister(1, registerRequest);

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        verify(registerService, times(1)).editRegisterById(1, registerRequest);
    }
}