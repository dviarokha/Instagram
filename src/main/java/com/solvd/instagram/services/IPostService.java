package com.solvd.instagram.services;

import com.solvd.instagram.models.Post;

import java.sql.SQLException;
import java.util.Optional;

public interface IPostService {
    Optional<Post> findById(Long id) throws SQLException;
    Post update(Post post) throws SQLException;
}
