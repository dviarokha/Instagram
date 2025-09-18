package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Like;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {
    void insert(Like like);
    void deleteLikeByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
    int countLikeForPost(@Param("postId") Long postId);
    int countLikeForUser(@Param("userId") Long userId);
}
