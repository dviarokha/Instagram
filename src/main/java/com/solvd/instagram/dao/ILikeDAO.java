package com.solvd.instagram.dao;

import com.solvd.instagram.models.Like;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public interface ILikeDAO<T> extends IBaseDAO<T> {
    List<T> getAllLikes() throws SQLException;
    T findByLikedAt(LocalTime likedAt) throws SQLException;
    List<Like> findByPostId(Long postId) throws SQLException;
    T findByUserId(Long userId) throws SQLException;
}
