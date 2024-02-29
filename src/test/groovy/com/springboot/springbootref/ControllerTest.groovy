package com.springboot.springbootref

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.times
import com.springboot.springbootref.registration.Register
import com.springboot.springbootref.registration.RegisterRequest
import com.springboot.springbootref.registration.RegisterService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito


@CompileStatic
class ControllerTest {

    private Controller controller

    @Mock
    private RegisterService registerService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this)
        controller = new Controller(registerService)
    }

    @Test
    void testGetRegisters() {
        // Mock data
        List<Register> registers = []
        // Add mock behavior
        Mockito.when(registerService.registers()).thenReturn(registers)

        // Test
        List<Register> result = controller.registers()

        // Verify
        Assertions.assertEquals(registers, result)
        Mockito.verify(registerService, times(1)).registers()
    }

    @Test
    void testGSearchRegister() {
        // Mocking the behavior of RegisterService
        Register register = new Register()
        Mockito.when(registerService.register(1)).thenReturn(Optional.of(register))

        // Testing the controller method
        Optional<Register> result = controller.register(1)

        Assertions.assertEquals(register, result.get())
        Mockito.verify(registerService, times(1)).register(1)
    }

    @Test
    void testPostRegister() {
        RegisterRequest registerRequest = new RegisterRequest(1, 'ritha', 'hello', '9384979966',
                'ritha@gmail.com', 'correct', 'csvData', 'yes')
        Mockito.when(registerService.addRegister(Mockito.any(RegisterRequest))).thenReturn('{\"status\": 200}')

        ResponseEntity<String> responseEntity = controller.saveRegister(registerRequest)

        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.statusCode.value())
        Mockito.verify(registerService, times(1)).addRegister(Mockito.any(RegisterRequest))
    }

    @Test
    void testDeleteRegister() {
        // Mock data
        Integer userId = 1
        boolean success = true
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body('Deleted')
        // Add mock behavior
        Mockito.when(registerService.deleteRegisterById(userId)).thenReturn(success)

        // Test
        ResponseEntity<String> result = controller.deleteRegister(userId)

        // Verify
        Assertions.assertEquals(responseEntity, result)
        Mockito.verify(registerService, times(1)).deleteRegisterById(userId)
    }

    @Test
    void testEditRegister() {
        RegisterRequest registerRequest = new RegisterRequest(1, 'ritha', 'hello', '9384979966',
                'ritha@gmail.com', 'correct', 'csvData', 'yes')
        Mockito.when(registerService.editRegisterById(1, registerRequest)).thenReturn(true)

        ResponseEntity<String> responseEntity = controller.editRegister(1, registerRequest)

        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.statusCode.value())
        Mockito.verify(registerService, times(1)).editRegisterById(1, registerRequest)
    }

}
