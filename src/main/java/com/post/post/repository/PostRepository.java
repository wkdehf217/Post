package com.post.post.repository;

import com.post.post.dto.PostRequestDto;
import com.post.post.dto.PostResponseDto;
import com.post.post.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;
    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Post save(Post Post) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO Post (pw, title, username, content, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setLong(1, Post.getPw());
                    preparedStatement.setString(2, Post.getTitle());
                    preparedStatement.setString(3, Post.getUsername());
                    preparedStatement.setString(4, Post.getContent());
                    preparedStatement.setString(5, Post.getDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        Post.setId(id);

        return Post;
    }
    public List<PostResponseDto> findPost(Long id) {
        // DB 조회
        String sql = String.format("SELECT id, title, username, content, date FROM Post WHERE id = '%d'",id);

        return jdbcTemplate.query(sql, new RowMapper<PostResponseDto>() {
            @Override
            public PostResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Post 데이터들을 PostResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String contents = rs.getString("content");
                String date = rs.getString("date");
                return new PostResponseDto(id,title,username, contents,date);
            }
        });
    }

    public List<PostResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM Post";

        return jdbcTemplate.query(sql, new RowMapper<PostResponseDto>() {
            @Override
            public PostResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Post 데이터들을 PostResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String contents = rs.getString("content");
                String date = rs.getString("date");
                return new PostResponseDto(id,title,username, contents,date);
            }
        });
    }

    public void update(Long id, Long pw, PostRequestDto requestDto) {
        String sql = "UPDATE Post SET title = ?, username = ?, content = ? WHERE id = ? AND pw = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getUsername(), requestDto.getContent(), id, pw);
    }

    public void delete(Long id, Long pw) {
        String sql = "DELETE FROM Post WHERE id = ? AND pw = ?";
        jdbcTemplate.update(sql,id, pw);
    }

    public Post findById(Long id,Long pw) {
        try{
            // DB 조회
            String sql = String.format("SELECT * FROM Post WHERE id = %d AND pw = %d",id,pw);
            return jdbcTemplate.query(sql, resultSet -> {
                if(resultSet.next()) {
                    Post Post = new Post();
                    Post.setTitle(resultSet.getString("title"));
                    Post.setUsername(resultSet.getString("username"));
                    Post.setContent(resultSet.getString("content"));
                    return Post;
                } else {
                    return null;
                }
            });
        }catch (Exception e){
            return null;
        }

    }
}
