package com.SpringBoot.springbootRef.Registration;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private final RegisterDao registerDao;

    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }
    public List<Register> GetRegisters(){
        return registerDao.selectallRegister();
    }
    public Optional<Register> GetRegister(Integer Id){
        return registerDao.selectRegister(Id);
    }

    public String addRegister(RegisterRequest registerrequest) {
        String Json;
        if(!registerDao.duplicateEmail(registerrequest.email())) {
            Register register = new Register(registerrequest.username(), registerrequest.password(), registerrequest.phonenumber(), registerrequest.email(), registerrequest.profile(), registerrequest.csvData(),registerrequest.verified());
            System.out.println(register.phonenumber);
            boolean IsSuccess = registerDao.insertRegister(register);
            if(IsSuccess){
                Json = "{\"status\": 200, \"headers\": {\"Content-Type\": \"application/json\"}, \"body\": \"Registered and email sent successfully!!!\"}";
            }
            else{
                Json = "{\"status\": 400, \"headers\": {\"Content-Type\": \"application/json\"}, \"body\": \"Unable to save the user.\"}";
            }
        }
        else{
            Json = "{\"status\": 302, \"headers\": {\"Content-Type\": \"application/json\"}, \"body\": \"Email Found, Unable to register!!!\"}";
        }
        return Json;

    }

    public boolean deleteRegisterById(Integer id) {
        if(registerDao.existId(id)){
            registerDao.deleteRegister(id);
            return true;
        }
        else{
            return false;
        }
    }
    public Register getRegister(Integer Id){
        Optional<Register> register = GetRegister(Id);
        Register r1 = null;
        if(register.isPresent()){
            r1 = new Register(register.get().getUsername(),register.get().getPassword(),register.get().getPhonenumber(),register.get().getEmail(),register.get().getProfile(),
                    register.get().getCsvData(),register.get().getVerified());
        }
        return r1;


    }
    public boolean editRegisterById(Integer id,RegisterRequest registerRequest) {
        if(registerDao.existId(id)){
            Register register = getRegister(id);


            register.setUsername(registerRequest.username());
            register.setPassword(registerRequest.password());
            register.setEmail(registerRequest.email());
            register.setPhonenumber(registerRequest.phonenumber());
            register.setProfile(registerRequest.profile());
            register.setCsvData(registerRequest.csvData());
            register.setVerified(registerRequest.verified());
            return registerDao.editRegister(register);
        }
        else{
            return false;
        }
    }
}
