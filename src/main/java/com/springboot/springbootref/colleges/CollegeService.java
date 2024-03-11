package com.springboot.springbootref.colleges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    private final CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public List<College> findAll() {
        return collegeRepository.findAll();
    }

    public College findById(Long id) {
        return collegeRepository.findById(id).orElse(null);
    }

    public College save(College college) {
        return collegeRepository.save(college);
    }

    public void deleteById(Long id) {
        collegeRepository.deleteById(id);
    }
    public College edit(Long id,College college){
        College col = findById(id);
        if(col != null){
            col.setName(college.getName());
            col.setLocation(college.getLocation());
            col.setType(college.getType());
            col.setWebsite(college.getWebsite());
            col.setNumStudents(college.getNumStudents());
            return collegeRepository.save(col);
        }
        else{
            return null;
        }
    }
}
