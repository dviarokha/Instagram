package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.User;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import com.solvd.instagram.mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.util.List;

public class UserMapperImpl implements UserMapper {
    @Override
    public User getById(Long id) {
        try (SqlSession sqlSession = MybatisSessionHolder.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getById(id);
        }
    }

    @Override
    public List<User> getUsers() {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUsers();
        }
    }

    @Override
    public User findByEmail(String email) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.findByEmail(email);
            return user;
        }
    }

    @Override
    public User findByPhone(String phone) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.findByPhone(phone);
            return user;
        }
    }

    @Override
    public User findByDateOfBirth(LocalDate dateOfBirth) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.findByDateOfBirth(dateOfBirth);
            return user;
        }
    }
}
