package com_springboot_springbootref.aggregate;

import com_springboot_springbootref.cqrs.Product;
import com_springboot_springbootref.cqrs.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AggregateService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProductService productService;

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Student getStudentByName(String name) {
        return studentService.getStudentByName(name);
    }

    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }

    public Student updateStudent(Long id, Student student) {
        return studentService.updateStudent(id, student);
    }

    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
    }

    public List<Product> getProducts() {
        return productService.getProducts();
    }

    public Optional<Product> getProduct(Long id) {
        return productService.getProduct(id);
    }
}
