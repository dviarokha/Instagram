package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.models.Like;

import java.time.LocalTime;
import java.util.List;

public interface LikeMapper {
    void insert(Like like);
    void update(Like like);
    void delete(Long id);
    Like getById(Long id);
    List<Like> getAllLikes();
    Like findByLikedAt(LocalTime likedAt);
    List<Like> findByPostId(Long postId);
    Like findByUserId(Long userId);


}
