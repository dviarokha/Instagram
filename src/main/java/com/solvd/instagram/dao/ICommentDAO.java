package com.solvd.instagram.dao;

import com.solvd.instagram.models.Comment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ICommentDAO<T> extends IBaseDAO<T> {
    List<T> getAllComments() throws SQLException;
    T findByCommentedAt(LocalDateTime commentedAt)  throws SQLException;
    T findByTextComment(String textComment) throws SQLException;
    List<Comment> findByPostId(long postId) throws SQLException;
    List<Comment> findByUserId(long userId) throws SQLException;
}
