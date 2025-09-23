package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.dao.IPostTagDAO;
import com.solvd.instagram.models.PostTag;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class PostTagMyBatis implements IPostTagDAO<PostTag> {
    SqlSessionFactory sqlSessionFactory = MyBatisSQLSessionFactory.getSqlSessionFactory();

    @Override
    public List<PostTag> getAllPostTags() throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            return postTagDAO.getAllPostTags();
        }
    }

    @Override
    public List<PostTag> findByTagId(long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            return postTagDAO.findByTagId(id);
        }
    }

    @Override
    public List<PostTag> findByPostId(long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            return postTagDAO.findByPostId(id);
        }
    }

    @Override
    public PostTag insert(PostTag entity) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            postTagDAO.insert(entity);
            return entity;
        }
    }

    @Override
    public PostTag getById(Long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            return (PostTag) postTagDAO.getById(id);
        }
    }

    @Override
    public PostTag update(PostTag entity) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            postTagDAO.update(entity);
            return entity;
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            IPostTagDAO postTagDAO = sqlSession.getMapper(IPostTagDAO.class);
            postTagDAO.removeById(id);
        }
    }
}
