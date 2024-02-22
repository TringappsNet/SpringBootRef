package com.SpringBoot.springbootRef;

import com.SpringBoot.springbootRef.Registration.Register;
import com.SpringBoot.springbootRef.Registration.RegisterRequest;
import com.SpringBoot.springbootRef.Registration.RegisterService;
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
    public List<Register> GetRegisters(){
        return registerService.GetRegisters();
    }

    @GetMapping("api/v1/registers/{register_Id}")
    public Optional<Register> GetRegister(@PathVariable("register_Id") Integer Id){
        return registerService.GetRegister(Id);
    }
    @PostMapping("api/v1/registers")
    public ResponseEntity<String> saveRegister(@RequestBody RegisterRequest registerrequest) throws Exception {

        System.out.println(registerrequest.username());

        String res = registerService.addRegister(registerrequest);
        ObjectMapper objectMapper = new ObjectMapper();
        int status = 400;
        //String resmessage = "";
        try {
            JsonNode jsonNode = objectMapper.readTree(res);
            // Extract the "status" value
            status = jsonNode.get("status").asInt();
            //resmessage = String.valueOf(jsonNode.get("body"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(status).body(res);
    }
    @DeleteMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> DeleteRegister(@PathVariable("register_id") Integer Id){
        boolean Success =registerService.deleteRegisterById(Id);
        if(Success){
            return ResponseEntity.status(200).body("Deleted");
        }
        else{
            return ResponseEntity.status(400).body("Unable to Delete");
        }
    }

    @PutMapping("api/v1/registers/{register_id}")
    public ResponseEntity<String> editRegister(@PathVariable("register_id") Integer Id,@RequestBody RegisterRequest registerRequest){
        boolean Success =registerService.editRegisterById(Id,registerRequest);
        if(Success){
            return ResponseEntity.status(200).body("Updated");
        }
        else{
            return ResponseEntity.status(400).body("Unable to Update");
        }
    }
}
