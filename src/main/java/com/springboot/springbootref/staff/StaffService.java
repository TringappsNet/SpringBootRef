package com.springboot.springbootref.staff;

import com.springboot.springbootref.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public List<Staff> allStaff() {
        return staffRepository.findAll();
    }
    public Staff getStaff(Long id) {
        return staffRepository.findById(id).orElse(null);
    }
    public Staff saveUser(Staff staff) {
        return staffRepository.save(staff);
    }
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
    public Staff edit(Staff staff, Long id) {
        Staff staff1 = getStaff(id);
        staff1.setName(staff.getName());
        staff1.setRole(staff.getRole());
        staffRepository.save(staff1);
        return staff1;
    }
}
