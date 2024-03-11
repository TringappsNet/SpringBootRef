package com.springboot.springbootref.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserJPAService {
    @Autowired
    private UserRepository userRepository;

    public List<User> UsersAll() {
        List<User> users =  userRepository.findAll();
        users.forEach(s -> s.setNotPersisted("It is not stored in database"));
        return users;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    public User edit(User user, Long id) {
        User user1 = findById(id);
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user1);
        return user1;
    }

}
