package com.springboot.springbootref;

import com.springboot.springbootref.user.User;
import com.springboot.springbootref.user.UserJPAService;
import com.springboot.springbootref.user.UserMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserJPAService userJPAService;
    @Autowired
    private UserMapperService userMapperService;

    @GetMapping
    public List<User> findAll() {
        return userJPAService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        userJPAService.save(user);
        return user;
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userMapperService.findById(id);
    }

    @PutMapping("/{id}/edit")
    public User edit(@PathVariable Long id, @RequestBody User user) {
        return userJPAService.edit(user,id);
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        userMapperService.deleteById(id);
        return "deleted";
    }

}
