package com_springboot_springbootref

import com_springboot_springbootref.Registration.Register
import com_springboot_springbootref.Registration.RegisterService
import com_springboot_springbootref.Registration.RegisterRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

class ControllerTest {
    @Mock
    RegisterService registerService

    Controller controller

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        controller = new Controller(registerService)
    }

    @Test
    void testGetRegisters() {
        // Mock data
        def registers = []
        // Add mock behavior
        when(registerService.getRegisters()).thenReturn(registers)

        // Test
        def result = controller.getRegisters()

        // Verify
        assertEquals(registers, result)
        verify(registerService, times(1)).getRegisters()
    }

    @Test
    void testGSearchRegister() {
        def register = new Register()
        when(registerService.getRegister(1)).thenReturn(Optional.of(register))

        def result = controller.getRegister(1)

        assertEquals(register, result.get())
        verify(registerService, times(1)).getRegister(1)
    }

    @Test
    void testPostRegister() throws Exception {
        def registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes")
        when(registerService.addRegister(any(RegisterRequest))).thenReturn("{\"status\": 200}")

        def responseEntity = controller.saveRegister(registerRequest)

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue())
        verify(registerService, times(1)).addRegister(any(RegisterRequest))
    }

    @Test
    void testDeleteRegister() {
        // Mock data
        def userId = 1
        def success = true
        def responseEntity = ResponseEntity.status(HttpStatus.OK).body("Deleted")

        when(registerService.deleteRegisterById(userId)).thenReturn(success)

        def result = controller.deleteRegister(userId)

        assertEquals(responseEntity, result)
        verify(registerService, times(1)).deleteRegisterById(userId)
    }

    @Test
    void testEditRegister() {
        def registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes")
        when(registerService.editRegisterById(1, registerRequest)).thenReturn(true)

        def responseEntity = controller.editRegister(1, registerRequest)

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue())
        verify(registerService, times(1)).editRegisterById(1, registerRequest)
    }
}
