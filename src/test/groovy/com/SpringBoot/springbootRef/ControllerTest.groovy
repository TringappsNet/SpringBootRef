import com.SpringBoot.springbootRef.Controller
import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControllerTest {

    private Controller controller;

    @Mock
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
     void testGSearchRegister() {
        // Mocking the behavior of RegisterService
        Register register = new Register();
        when(registerService.getRegister(1)).thenReturn(Optional.of(register));

        // Testing the controller method
        Optional<Register> result = controller.getRegister(1);

        assertEquals(register, result.get());
        verify(registerService, times(1)).getRegister(1);
    }

    @Test
     void testPostRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes");
        when(registerService.addRegister(any(RegisterRequest.class))).thenReturn("{\"status\": 200}");

        ResponseEntity<String> responseEntity = controller.saveRegister(registerRequest);

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        verify(registerService, times(1)).addRegister(any(RegisterRequest.class));
    }

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

    @Test
     void testEditRegister() {
        RegisterRequest registerRequest = new RegisterRequest(1, "ritha", "hello", "9384979966", "ritha@gmail.com", "correct", "csvData", "yes");
        when(registerService.editRegisterById(1, registerRequest)).thenReturn(true);

        ResponseEntity<String> responseEntity = controller.editRegister(1, registerRequest);

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        verify(registerService, times(1)).editRegisterById(1, registerRequest);
    }
}
