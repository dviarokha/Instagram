package com.solvd.instagram.mybatis.myBatisIMPL;

import com.solvd.instagram.models.Stories;
import com.solvd.instagram.mybatis.MybatisSessionHolder;
import com.solvd.instagram.mybatis.StoriesMapper;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;

public class StoriesMapperImpl implements StoriesMapper {

    @Override
    public void insert(Stories stories) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            StoriesMapper mapper = session.getMapper(StoriesMapper.class);
            mapper.insert(stories);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            StoriesMapper mapper = session.getMapper(StoriesMapper.class);
            mapper.deleteById(id);
        }
    }

    @Override
    public int countByCreatedAt(LocalDateTime createdAt) {
        try (SqlSession session = MybatisSessionHolder.getSqlSession()) {
            StoriesMapper mapper = session.getMapper(StoriesMapper.class);
            return mapper.countByCreatedAt(createdAt);
        }
    }
}
