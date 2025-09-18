package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.Post;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import com.solvd.instagram.mybatis.PostMapper;
import org.apache.ibatis.session.SqlSession;
import java.time.LocalDateTime;
import java.util.List;

public class PostMapperImpl implements PostMapper {
    @Override
    public void createPost(Post post) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.createPost(post);
        }
    }

    @Override
    public void deletePostById(int id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.deletePostById(id);
        }
    }

    @Override
    public void updatePost(Post post) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.updatePost(post);
        }
    }

    @Override
    public Post findPost(LocalDateTime postedAt, Long postTypeId, Long userId) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            Post post = mapper.findPost(postedAt,postTypeId,userId);
            return post;
        }
    }

    @Override
    public List<Post> findAllPosts() {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.findAllPosts();
        }
    }

    @Override
    public Post findByTypeAndUserId(Long postTypeId, Long userId) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            Post post = mapper.findByTypeAndUserId(postTypeId,userId);
            return post;
        }

    }
}
