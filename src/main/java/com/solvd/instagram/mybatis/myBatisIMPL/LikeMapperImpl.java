package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.Like;
import com.solvd.instagram.mybatis.LikeMapper;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class LikeMapperImpl implements LikeMapper {
    @Override
    public void insert(Like like) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            LikeMapper mapper = session.getMapper(LikeMapper.class);
            mapper.insert(like);
        }
    }

    @Override
    public void deleteLikeByUserAndPost(Long userId, Long postId) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            LikeMapper mapper = session.getMapper(LikeMapper.class);
            mapper.deleteLikeByUserAndPost(userId, postId);
        }
    }

    @Override
    public int countLikeForPost(Long postId) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            LikeMapper mapper = session.getMapper(LikeMapper.class);
            return mapper.countLikeForPost(postId);
        }
    }

    @Override
    public int countLikeForUser(Long userId) {
        try(SqlSession session = MybatisSessionHolder.getSqlSession()) {
            LikeMapper mapper = session.getMapper(LikeMapper.class);
            return mapper.countLikeForUser(userId);
        }
    }
}
