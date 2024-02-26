package com.SpringBoot.springbootRef.ResgistrationTest;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterDataAccessService;
import com.SpringBoot.springbootRef.Registration.RegisterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegisterDataAccessTest {
    @Mock
    private RegisterRepository registerRepository;
    @InjectMocks
    private RegisterDataAccessService registerDataAccessService;
    @Test
    public void testSelectAllRegister() {
        List<Register> registers = new ArrayList<>();
        registers.add(new Register("user1", "password1", 12345, "email1@example.com", "profile1", "csvData1", "Yes"));
        when(registerRepository.findAll()).thenReturn(registers);
        List<Register> result = registerDataAccessService.selectallRegister();
        assertEquals(registers, result);
    }
    @Test
    public void testSelectRegister() {
        Register register = new Register("user1", "password1", 12345, "email1@example.com", "profile1", "csvData1", "Yes");
        Optional<Register> optionalRegister = Optional.of(register);
        when(registerRepository.findById(1)).thenReturn(optionalRegister);
        Optional<Register> result = registerDataAccessService.selectRegister(1);
        assertTrue(result.isPresent());
        assertEquals(register, result.get());
    }
}
