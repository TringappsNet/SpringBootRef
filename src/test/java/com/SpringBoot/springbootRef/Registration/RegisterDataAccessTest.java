package com.SpringBoot.springbootRef.Registration;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterDataAccessTest {
    @Mock
    private RegisterRepository registerRepository;
    @InjectMocks
    private RegisterDataAccessService registerDataAccessService;
    @Test
    public void testSelectAllRegister() {
        List<Register> registers = new ArrayList<>();
        registers.add(new Register(1,"user1", "password1", "12345", "email1@example.com", "profile1", "csvData1", "Yes"));
        when(registerRepository.findAll()).thenReturn(registers);
        List<Register> result = registerDataAccessService.selectAllRegister();
        assertEquals(registers, result);
    }
    @Test
    public void testSelectRegister() {
        Register register = new Register(2,"user1", "password1", "12345", "email1@example.com", "profile1", "csvData1", "Yes");
        Optional<Register> optionalRegister = Optional.of(register);
        when(registerRepository.findById(1)).thenReturn(optionalRegister);
        Optional<Register> result = registerDataAccessService.selectRegister(1);
        assertTrue(result.isPresent());
        assertEquals(register, result.get());
    }
}
