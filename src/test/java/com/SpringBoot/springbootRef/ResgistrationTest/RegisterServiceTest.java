package com.SpringBoot.springbootRef.ResgistrationTest;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterDao;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegisterServiceTest {

    @Mock
    private RegisterDao registerDao;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        reset(registerDao);
    }

    @Test
    void testGetRegisters() {
        Register register1 = new Register("user1", "password1", 12345, "email1@example.com", "profile1", "csvData1", "Yes");
        Register register2 = new Register("user2", "password2", 98765, "email2@example.com", "profile2", "csvData2", "No");
        when(registerDao.selectallRegister()).thenReturn(Arrays.asList(register1, register2));
        List<Register> result = registerService.GetRegisters();
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
        verify(registerDao, times(1)).selectallRegister();
    }

    @Test
    void testGetRegister() {
        Register register = new Register("user1", "password1", 12345, "email1@example.com", "profile1", "csvData1", "Yes");
        when(registerDao.selectRegister(1)).thenReturn(Optional.of(register));
        Optional<Register> result = registerService.GetRegister(1);
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
        verify(registerDao, times(1)).selectRegister(1);
    }

}
