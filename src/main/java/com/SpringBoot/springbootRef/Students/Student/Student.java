package com.SpringBoot.springbootRef.Students.Student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

import javax.validation.constraints.*;

@Entity
@Table(name = "student_Record")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String course;

    public static Student build(long id, String name, String email, String course) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        return student;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
