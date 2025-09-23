package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.dao.ICommentDAO;
import com.solvd.instagram.models.Comment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class CommentMyBatis implements ICommentDAO<Comment> {
    SqlSessionFactory sqlSessionFactory = MyBatisSQLSessionFactory.getSqlSessionFactory();

    @Override
    public List<Comment> getAllComments() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.getAllComments();
        }
    }

    @Override
    public Comment findByCommentedAt(LocalDateTime commentedAt) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.findByCommentedAt(commentedAt);
        }
    }

    @Override
    public Comment findByTextComment(String textComment) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.findByTextComment(textComment);
        }
    }

    @Override
    public List<Comment> findByPostId(long postId) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.findByPostId(postId);
        }
    }

    @Override
    public List<Comment> findByUserId(long userId) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.findByUserId(userId);
        }
    }

    @Override
    public Comment insert(Comment entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            mapper.insert(entity);
            return entity;
        }
    }

    @Override
    public Comment getById(Long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            return mapper.selectByCommentId(id);
        }
    }

    @Override
    public Comment update(Comment entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            mapper.update(entity);
            return entity;
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            CommentMapper mapper = session.getMapper(CommentMapper.class);
            mapper.delete(id);
        }
    }
}
