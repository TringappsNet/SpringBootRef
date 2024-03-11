package com.springboot.springbootref.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserMapperService implements RowMapper{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PostRepository postRepository;

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        //Long latestPostId = rs.getLong("latest_post_id");
        //Post latestPost = postRepository.findAllByAuthorId(user.getId(),pageable).orElse(null); // Replace with your appropriate fetching method
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setCreatedAt(rs.getObject("createdat", LocalDateTime.class));
        user.setUpdatedAt(rs.getObject("updatedat", LocalDateTime.class));
        user.setVersion(rs.getLong("version"));
        //user.setPosts(latestPost);
        user.setNotPersisted("It is not stored in database");
        return user;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,this::mapRow);
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, id);
    }

    public User save(User user) {
        String sql = "INSERT INTO users (username, email,created_at,updated_at) VALUES (?, ?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), LocalDateTime.now(),LocalDateTime.now());
        return user;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        return new int[0];
    }
}
