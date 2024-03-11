package com.springboot.springbootref.user;

import com.springboot.springbootref.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
//    @PersistenceContext
//    private EntityManager entityManager;
//    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public PostService(PostRepository postRepository,UserRepository userRepository){ //,@Qualifier("userpostentityManagerFactory") EntityManagerFactory entityManagerFactory
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        //this.entityManagerFactory = entityManagerFactory;
    }

    public Post createPost(PostRequest postRequest) throws UserNotFoundException {
//        System.out.println(postRequest.getUserId());
//        System.out.println(postRequest.getContent());
//
//        User author = userRepository.findById(postRequest.getUserId())
//                .orElseThrow(() -> new UserNotFoundException("User with ID " + postRequest.getUserId() + " not found"));
//        Post post = new Post(postRequest.getTitle(), postRequest.getContent(), LocalDateTime.now() ,author);
//        System.out.println(post.getContent());
//        return postRepository.save(post);
        User author = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + postRequest.getUserId() + " not found"));

        Post post = new Post(postRequest.getTitle(), postRequest.getContent(), LocalDateTime.now(), author);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with ID " + id + " not found"));
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    public List<PostSummary> getPostsByAuthor(Long author_id){
        return postRepository.findAllPostsByAuthorId(author_id);
    }


}
