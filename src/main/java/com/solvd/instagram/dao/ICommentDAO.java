package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface ICommentDAO<T> extends IBaseDAO<T> {
    List<T> getAllComments() throws SQLException;
    T getCommentsByCommentedAt(LocalDateTime commentedAt)  throws SQLException;
    T getCommentsByTextComment(String textComment) throws SQLException;
    T getAllCommentsByUserId(long userId) throws SQLException;
    T getAllCommentsByPostId(long postId) throws SQLException;
}
