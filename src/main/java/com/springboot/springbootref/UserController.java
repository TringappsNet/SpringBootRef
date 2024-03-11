package com.springboot.springbootref;


import com.springboot.springbootref.exceptions.UserNotFoundException;
import com.springboot.springbootref.user.Post;
import com.springboot.springbootref.user.PostRequest;
import com.springboot.springbootref.user.PostService;
import com.springboot.springbootref.user.PostSummary;
import com.springboot.springbootref.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springboot.springbootref.LoggerUtil.getLogger;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private final UserJPAService userJPAService;
    @Autowired
    private final UserMapperService userMapperService;
    @Autowired
    private final PostService postService;
    public UserController(UserJPAService userJPAService, UserMapperService userMapperService, PostService postService) {
        this.userJPAService = userJPAService;
        this.userMapperService = userMapperService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        getLogger().info("Fetching All users");
        return userJPAService.UsersAll();

    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        System.out.println(user.getId());

        //userJPAService.save(user);
        userJPAService.saveUser(user);
        getLogger().info("User saved!!!");
        return user;
    }
    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        try{
            return userMapperService.findById(id);
        }
        catch (Exception e){
            getLogger().info("User Not found");

            throw new UserNotFoundException("User not found exception");
        }
    }

    @PutMapping("/users/{id}/edit")
    public User edit(@PathVariable Long id, @RequestBody User user) {
        return userJPAService.edit(user,id);
    }
    @DeleteMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id) {
        userMapperService.deleteById(id);
        return "deleted";
    }

    @GetMapping("/posts")
    public List<Post> allPost(){
        return postService.getAllPosts();
    }
    @GetMapping("/posts/{id}")
    public Post findPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        try {
            Post createdPost = postService.createPost(postRequest);
            return ResponseEntity.ok(createdPost);
        } catch (UserNotFoundException e) {
            getLogger().error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/posts/{id}/edit")
    public ResponseEntity<Post> editPost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        Post existingPost = postService.getPostById(id);
        if(existingPost == null){
            getLogger().error("ID not exist for post to update");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else{
            Post updatedPost = postService.updatePost(id,postRequest.getTitle(),postRequest.getContent());
            return ResponseEntity.ok(updatedPost);
        }

    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post Successfully Deleted!!!");
    }
    @GetMapping("users/posts/{id}")
    public List<PostSummary> findPostsByAuthor(@PathVariable Long id) {
        return postService.getPostsByAuthor(id);
    }

}
