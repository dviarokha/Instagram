package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Stories;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface StoriesMapper {
    void insert(Stories stories);
    void deleteById(@Param("id") Long id);
    int countByCreatedAt(@Param("createdAt") LocalDateTime createdAt);
}
