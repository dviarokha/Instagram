package com.solvd.instagram.dao;

import com.solvd.instagram.models.PostTag;

import java.sql.SQLException;
import java.util.List;

public interface IPostTagDAO<T> extends IBaseDAO<T> {
    List<PostTag> getAllPostTags() throws SQLException;
    T getPostTagByTagId(long id) throws SQLException;
    T getPostTagByPostId(long id) throws SQLException;
}
