package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.dao.ILikeDAO;
import com.solvd.instagram.models.Like;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class LikeMyBatis implements ILikeDAO<Like> {
    SqlSessionFactory sqlSessionFactory = MyBatisSQLSessionFactory.getSqlSessionFactory();

    @Override
    public List<Like> getAllLikes() throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            return likeMapper.getAllLikes();
        }
    }

    @Override
    public Like findByLikedAt(LocalTime likedAt) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            return likeMapper.findByLikedAt(likedAt);
        }
    }

    @Override
    public List<Like> findByPostId(Long postId) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            return likeMapper.findByPostId(postId);
        }
    }

    @Override
    public Like findByUserId(Long userId) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            return likeMapper.findByUserId(userId);
        }
    }

    @Override
    public Like insert(Like entity) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            likeMapper.insert(entity);
            return entity;
        }
    }

    @Override
    public Like getById(Long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            return likeMapper.getById(id);
        }
    }

    @Override
    public Like update(Like entity) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            likeMapper.update(entity);
            return entity;
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            LikeMapper likeMapper = sqlSession.getMapper(LikeMapper.class);
            likeMapper.delete(id);
        }
    }
}
