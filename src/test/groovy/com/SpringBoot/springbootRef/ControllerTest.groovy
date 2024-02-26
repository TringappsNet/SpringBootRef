package com.SpringBoot.springbootRef;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControllerTests {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        registerService = mock(RegisterService.class);
        controller = new Controller(registerService);
    }

    @Test
    void testGetRegisters() {
        // Mock data
        List<Register> registers = new ArrayList<>();
        // Add mock behavior
        when(registerService.getRegisters()).thenReturn(registers);

        // Test
        List<Register> result = controller.getRegisters();

        // Verify
        assertEquals(registers, result);
        verify(registerService, times(1)).getRegisters();
    }

    @Test
    void testSearchRegister() {
        // Mock data
        Integer userId = 1;
        Register register = new Register();
        Optional<Register> optionalRegister = Optional.of(register);
        // Add mock behavior
        when(registerService.getRegister(userId)).thenReturn(optionalRegister);

        // Test
        Optional<Register> result = controller.getRegister(userId);

        // Verify
        assertEquals(optionalRegister, result);
        verify(registerService, times(1)).getRegister(userId);
    }

//    @Test
//    void testSaveRegister() throws Exception {
//        // Mock data
//        RegisterRequest registerRequest = new RegisterRequest();
//        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body("Success");
//        // Add mock behavior
//        when(registerService.addRegister(registerRequest)).thenReturn("Success");
//
//        // Test
//        ResponseEntity<String> result = controller.saveRegister(registerRequest);
//
//        // Verify
//        assertEquals(responseEntity, result);
//        verify(registerService, times(1)).addRegister(registerRequest);
//    }

    @Test
    void testDeleteRegister() {
        // Mock data
        Integer userId = 1;
        boolean success = true;
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body("Deleted");
        // Add mock behavior
        when(registerService.deleteRegisterById(userId)).thenReturn(success);

        // Test
        ResponseEntity<String> result = controller.deleteRegister(userId);

        // Verify
        assertEquals(responseEntity, result);
        verify(registerService, times(1)).deleteRegisterById(userId);
    }

//    @Test
//    void testEditRegister() {
//        // Mock data
//        Integer userId = 1;
//        RegisterRequest registerRequest = new RegisterRequest();
//        boolean success = true;
//        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body("Updated");
//        // Add mock behavior
//        when(registerService.editRegisterById(userId, registerRequest)).thenReturn(success);
//
//        // Test
//        ResponseEntity<String> result = controller.editRegister(userId, registerRequest);
//
//        // Verify
//        assertEquals(responseEntity, result);
//        verify(registerService, times(1)).editRegisterById(userId, registerRequest);
//    }
}
