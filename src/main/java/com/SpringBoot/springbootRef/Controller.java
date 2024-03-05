package com.SpringBoot.springbootRef;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class Controller {

    private final RegisterService registerService;
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("api/v1/registers")
    public List<Register> getRegisters() {
        logger.info("Retrieving all registers");
        return registerService.getRegisters();
    }

    @GetMapping("api/v1/registers/{register_Id}")
    public Optional<Register> getRegister(@PathVariable("register_Id") Integer Id) {
        Optional<Register> register = registerService.getRegister(Id);
        if (register.isPresent()) {
            logger.info("Successfully retrieved register with ID: {}", Id);
        } else {
            logger.warn("Register with ID: {} not found", Id);
        }
        return register;
    }

    @PostMapping("api/v1/registers")
    public ResponseEntity<String> saveRegister(@RequestBody RegisterRequest registerRequest) throws Exception {

        System.out.println(registerRequest.username()); // Use logger instead: logger.info("Username: {}", registerRequest.username());

        String result = registerService.addRegister(registerRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        int status = 400;

        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            status = jsonNode.get("status").asInt();
        } catch (Exception e) {
            logger.error("Error processing response: {}", e.getMessage()); // Log error message
        }

        return ResponseEntity.status(status).body(result);
    }

    @DeleteMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> deleteRegister(@PathVariable("register_id") Integer Id) {
        boolean success = registerService.deleteRegisterById(Id);
        if (success) {
            logger.info("Register with ID: {} deleted successfully", Id);
            return ResponseEntity.status(200).body("Deleted");
        } else {
            logger.warn("Failed to delete register with ID: {}", Id);
            return ResponseEntity.status(400).body("Unable to Delete");
        }
    }

    @PutMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> editRegister(@PathVariable("register_id") Integer Id, @RequestBody RegisterRequest registerRequest) {
        boolean success = registerService.editRegisterById(Id, registerRequest);
        if (success) {
            logger.info("Register with ID: {} updated successfully", Id);
            return ResponseEntity.status(200).body("Updated");
        } else {
            logger.warn("Failed to update register with ID: {}", Id);
            return ResponseEntity.status(400).body("Unable to Update");
        }
    }
}
