package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public interface ILikeDAO<T> extends IBaseDAO<T> {
    List<T> getAllLikes() throws SQLException;
    T getLikesByLikedAt(LocalTime likedAt) throws SQLException;
    T getLikesByPostId(Long postId) throws SQLException;
    T getLikesByUserId(Long userId) throws SQLException;
}
