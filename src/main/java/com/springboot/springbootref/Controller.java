package com.springboot.springbootref;

import com.springboot.springbootref.registration.RegisterRequest;
import com.springboot.springbootref.registration.Register;
import com.springboot.springbootref.registration.RegisterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class Controller {
    private final RegisterService registerService;

    public Controller(RegisterService registerService) {
        this.registerService = registerService;
    }
    @GetMapping("api/v1/registers")
    public List<Register> registers() {
        return registerService.registers();
    } // retrieving all users from the register Service.

    @GetMapping("api/v1/registers/{register_Id}")
    public Optional<Register> register(@PathVariable("register_Id") Integer Id) {
        return registerService.register(Id);
    } // retrieving single user from the register Service based on the id.

    @PostMapping("api/v1/registers")
    public ResponseEntity<String> saveRegister(@RequestBody RegisterRequest registerrequest) throws Exception {

        System.out.println(registerrequest.username());

        String result = registerService.addRegister(registerrequest); // Adding user in Database using register service fetch the response in result variable
        ObjectMapper objectMapper = new ObjectMapper();
        int status = 400;

        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            // Extract the "status" value
            status = jsonNode.get("status").asInt();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).body(result); // returning Response to post method
    }

    @DeleteMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> deleteRegister(@PathVariable("register_id") Integer Id) {
        boolean Success =registerService.deleteRegisterById(Id); // Delete the user details using register service deleteRegisterById return response.
        if(Success){
            return ResponseEntity.status(200).body("Deleted"); // If successfully deleted Sending response as OK.
        }
        else{
            return ResponseEntity.status(400).body("Unable to Delete");// If user not deleted Sending response as Bad Request.
        }
    }

    @PutMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> editRegister(@PathVariable("register_id") Integer Id,@RequestBody RegisterRequest registerRequest) {
        boolean Success =registerService.editRegisterById(Id,registerRequest); // Edit the user details using register service editRegisterById return response.
        if(Success){
            return ResponseEntity.status(200).body("Updated");// If successfully edited Sending response as OK.
        }
        else{
            return ResponseEntity.status(400).body("Unable to Update");// If record not edited Sending response as Bad Request.
        }
    }
}
