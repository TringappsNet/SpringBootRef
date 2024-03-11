package com.springboot.springbootref.registration;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegisterDataAccessService implements RegisterDao{
    private final RegisterRepository registerRepository;

    public RegisterDataAccessService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public List<Register> selectAllRegister() {
        return registerRepository.findAll(); // Using repository Getting all user details.
    }

    @Override
    public Optional<Register> selectRegister(Integer Id) {
        return registerRepository.findById(Id); // Using repository Getting single user record based on their ID..
    }

    @Override
    public boolean insertRegister(Register register) { // Inserting user in Database.
        try{
            //System.out.println(register.email);
            register.verified = "No"; // initially setting the Verified is No
            registerRepository.save(register); //Save the user in Database.
            System.out.println("Record Saved!!!");
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean duplicateEmail(String email) { // Checking email is already registered or not.
        System.out.println(email);
        System.out.println(registerRepository.findAll().stream().anyMatch(s -> s.getEmail().equals(email)));
        return registerRepository.findAll().stream().anyMatch(s -> s.getEmail().equals(email));
    }


    @Override
    public boolean existId(Integer Id) { // Checking the user is there or not.
        Optional<Register> r = registerRepository.findById(Id); //retrieving the user
        return r.isPresent();
    }

    @Override
    public void deleteRegister(Integer Id) { // Delete the user record from database.
        try {
            registerRepository.deleteById(Id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean editRegister(Register register) { // Edit the user Record
        try{
            //System.out.println(register.email);
            registerRepository.save(register); // Saving the user record in database.
            System.out.println("Record Saved!!!");
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }




}
