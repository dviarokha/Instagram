package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.dao.IPostDAO;
import com.solvd.instagram.models.Post;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PostMyBatis implements IPostDAO<Post> {
    SqlSessionFactory sqlSessionFactory = MyBatisSQLSessionFactory.getSqlSessionFactory();

    @Override
    public List<Post> getAllPosts() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.getAll();
        }
    }

    @Override
    public Post findByPostedAt(LocalDateTime postedAt) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.findByPostedAt(postedAt);
        }
    }

    @Override
    public List<Post> findByPostTypeId(long postTypeId) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.findByPostTypeId(postTypeId);
        }
    }

    @Override
    public List<Post> findByUserId(long userId) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return mapper.findByUserId(userId);
        }
    }

    @Override
    public Post insert(Post entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.insert(entity);
            return entity;
        }
    }

    @Override
    public Post getById(Long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            return  mapper.selectById(id);
        }
    }

    @Override
    public Post update(Post entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.update(entity);
            return entity;
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            PostMapper mapper = session.getMapper(PostMapper.class);
            mapper.deleteById(id);
        }
    }
}
