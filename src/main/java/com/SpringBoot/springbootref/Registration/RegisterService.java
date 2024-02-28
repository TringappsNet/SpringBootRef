package com.SpringBoot.springbootref.Registration;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private final RegisterDao registerDao;

    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }
    public List<Register> getRegisters() {
        return registerDao.selectAllRegister(); // retrieving all users from the repository.
    }
    public Optional<Register> getRegister(Integer Id) {
        return registerDao.selectRegister(Id);// retrieving single user from the repository based on the id.
    }

    public String addRegister(RegisterRequest registerrequest) {
        String Json;

        if(!registerDao.duplicateEmail(registerrequest.email())) { // Checking the email Id is already registered or not.

            Register register = new Register(registerrequest.Id(),registerrequest.username(), registerrequest.password(), registerrequest.phoneNumber(),
                    registerrequest.email(), registerrequest.profile(), registerrequest.csvData(),registerrequest.verified()); // Creating user object for retrieved body from the user.

            // System.out.println(register.phoneNumber); // Printing Phone Number in console

            boolean IsSuccess = registerDao.insertRegister(register); //  Adding user in database using repository and fetch the response in IsSuccess variable

            if(IsSuccess){
                Json = "{\"status\": 200, \"headers\": {\"Content-Type\": \"application/json\"}, " +
                        "\"body\": \"Registered and email sent successfully!!!\"}"; // If Success Sending the HTTPCode OK.
            }
            else{
                Json = "{\"status\": 400, \"headers\": {\"Content-Type\": \"application/json\"}, " +
                        "\"body\": \"Unable to save the user.\"}"; // If any issue persists, Sending the HTTPCode Bad Request.
            }
        }
        else{
            Json = "{\"status\": 302, \"headers\": {\"Content-Type\": \"application/json\"}, " +
                    "\"body\": \"Email Found, Unable to register!!!\"}"; // If email is already registered then Sending the HTTPCode Bad Request.
        }
        return Json;

    }

    public boolean deleteRegisterById(Integer id) {
        if(registerDao.existId(id)){ // Checking the user is present or not.
            registerDao.deleteRegister(id);  // Deleting user Instance
            return true;
        }
        else{
            return false; // If user is not present return false.
        }
    }
    public Register fetchRegister(Integer Id){
        Optional<Register> register = getRegister(Id); // fetch the user
        Register r1 = null; // initially the register object r1 is null.

        if(register.isPresent()){ // If user is present setting the r1 null object to data.
            r1 = new Register(register.get().getId(),register.get().getUsername(),register.get().getPassword(),register.get().getPhoneNumber(),
                    register.get().getEmail(),register.get().getProfile(),
                    register.get().getCsvData(),register.get().getVerified());
        }
        return r1; // returning the user


    }
    public boolean editRegisterById(Integer id,RegisterRequest registerRequest) {
        if(registerDao.existId(id)){ // Check the user is present or not.
            Register register = fetchRegister(id); // Fetch the user for edit


            register.setUsername(registerRequest.username()); // setting the username
            register.setPassword(registerRequest.password());// setting the password
            register.setEmail(registerRequest.email());// setting the email
            register.setPhoneNumber(registerRequest.phoneNumber());// setting the phoneNumber
            register.setProfile(registerRequest.profile());// setting the profile
            register.setCsvData(registerRequest.csvData());// setting the csvData
            register.setVerified(registerRequest.verified());// setting the Verified or not
            return registerDao.editRegister(register);// Sending the register to Edit the register.
        }
        else{
            return false;
        }
    }
}
