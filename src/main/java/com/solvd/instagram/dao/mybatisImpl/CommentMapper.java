package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.models.Comment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface CommentMapper {
    void insert(Comment comment);
    void update(Comment comment);
    void delete(Long id);
    Comment selectByCommentId(Long id);
    List<Comment> getAllComments();
    Comment findByCommentedAt(LocalDateTime commentedAt);
    Comment findByTextComment(String textComment);
    List<Comment> findByPostId(long postId);
    List<Comment> findByUserId(long userId);
}
