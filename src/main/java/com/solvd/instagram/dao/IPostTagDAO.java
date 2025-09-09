package com.solvd.instagram.dao;

import com.solvd.instagram.models.PostTag;

import java.sql.SQLException;
import java.util.List;

public interface IPostTagDAO<T> extends IBaseDAO<T> {
    List<PostTag> getAllPostTags() throws SQLException;
    List<PostTag> findByTagId(long id) throws SQLException;
    List<PostTag> findByPostId(long id) throws SQLException;
}
