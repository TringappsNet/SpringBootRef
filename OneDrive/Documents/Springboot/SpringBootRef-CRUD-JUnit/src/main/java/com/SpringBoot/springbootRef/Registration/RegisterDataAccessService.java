package com.SpringBoot.springbootRef.Registration;

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
    public List<Register> selectallRegister() {
        return registerRepository.findAll();
    }

    @Override
    public Optional<Register> selectRegister(Integer Id) {
        return registerRepository.findById(Id);
    }

    @Override
    public boolean insertRegister(Register register) {
        try{
            System.out.println(register.email);
            register.verified = "No";
            registerRepository.save(register);
            System.out.println("Record Saved!!!");
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean duplicateEmail(String email) {
        return registerRepository.findAll().stream().anyMatch(s -> s.getEmail().equals(email));
    }


    @Override
    public boolean existId(Integer Id) {
        Optional<Register> r = registerRepository.findById(Id);
        if(r.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void deleteRegister(Integer Id) {
        try {
            registerRepository.deleteById(Id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean editRegister(Register register) {
        try{
            System.out.println(register.email);
            //register.verified = "No";
            registerRepository.save(register);
            System.out.println("Record Saved!!!");
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }




}
