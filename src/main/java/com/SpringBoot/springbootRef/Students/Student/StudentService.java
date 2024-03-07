package com.SpringBoot.springbootRef.Students.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private void validateStudentRequest(StudentRequest studentRequest) {
        if (studentRequest.getName() == null || studentRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        if (studentRequest.getEmail() == null || studentRequest.getEmail().isBlank() || !isValidEmail(studentRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (studentRequest.getCourse() == null || studentRequest.getCourse().isBlank()) {
            throw new IllegalArgumentException("Course cannot be null or blank");
        }

    }
    public Student saveStudent(@Valid StudentRequest studentRequest) {
        // Validate input parameters
        validateStudentRequest(studentRequest);

        // Convert StudentRequest to Student entity
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setCourse(studentRequest.getCourse());

        // Save the student in the database
        return repository.save(student);
    }


    public List<Student> getAllStudents() {
        return repository.findAll();
    }
    public Student updateStudent(Long id, StudentRequest studentRequest) throws StudentNotFoundException {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setCourse(studentRequest.getCourse());

        return repository.save(student);
    }

    public void deleteStudent(Long id) throws StudentNotFoundException {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        repository.delete(student);
    }
    public Student getStudentById(Long id) throws StudentNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

}