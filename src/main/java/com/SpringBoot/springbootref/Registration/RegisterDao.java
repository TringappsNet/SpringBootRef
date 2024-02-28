package com.SpringBoot.springbootref.Registration;

import java.util.List;
import java.util.Optional;

public interface RegisterDao {
    List<Register> selectAllRegister();

    Optional<Register> selectRegister(Integer Id);

    boolean insertRegister(Register register);

    boolean duplicateEmail(String email);

    boolean editRegister(Register register);


    boolean existId(Integer Id);

    void deleteRegister(Integer Id);


}
