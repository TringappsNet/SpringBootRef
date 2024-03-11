package com.springboot.springbootref.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT new com.springboot.springbootref.user.PostSummary(p.id, p.title, p.content) FROM Post p WHERE p.author.id = :authorId ")
    List<PostSummary> findAllPostsByAuthorId(@Param("authorId") Long authorId);

}
