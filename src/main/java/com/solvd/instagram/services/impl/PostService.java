package com.solvd.instagram.services.impl;

import com.solvd.instagram.dao.*;
import com.solvd.instagram.dao.mySQLIplm.*;
import com.solvd.instagram.exceptions.PostValidationException;
import com.solvd.instagram.models.*;
import com.solvd.instagram.services.IPostService;

import java.sql.SQLException;
import java.util.Optional;

public class PostService implements IPostService {

    private IPostDAO postDAO = new PostDAO();
    private ICommentDAO commentDAO = new CommentDAO();
    private ILikeDAO likeDAO = new LikeDAO();
    private IPostTagDAO postTagDAO = new PostTagDAO();


    @Override
    public Optional<Post> findById(Long id) throws SQLException {
        Post post = (Post) postDAO.getById(id);
        if (post == null) {
            return Optional.empty();
        }
        post.setComments(commentDAO.findByPostId(id));
        post.setLikes(likeDAO.findByPostId(id));
        post.setPostTags(postTagDAO.findByPostId(id));
        return Optional.of(post);
    }

    @Override
    public Post update(Post post) throws SQLException {
        if (post == null) {
            throw new IllegalArgumentException("Post is null");
        }
        Optional<Post> optionalPost = findById(post.getId());
        if (optionalPost.isPresent()) {
            Post existedPost = optionalPost.get();

            existedPost.setId(post.getId());
            existedPost.setPostedAt(post.getPostedAt());
            existedPost.setUserId(post.getUserId());
            existedPost.setPostTypeId(post.getPostTypeId());
            existedPost.setComments(post.getComments());
            existedPost.setLikes(post.getLikes());
            existedPost.setPostTags(post.getPostTags());

            updatePostTags(existedPost);
            updatePostLikes(existedPost);
            updatePostComments(existedPost);

            postDAO.update(existedPost);

            return existedPost;
        } else {
            throw new PostValidationException("Post is null");
        }
    }
    private void updatePostTags(Post post) throws SQLException {
        if (post.getPostTags() != null) {
            for (PostTag postTag : post.getPostTags()) {
                postTag.setPostId(post.getId());
                postTagDAO.update(postTag);
            }
        }
    }
    private void updatePostLikes(Post post) throws SQLException {
        if (post.getLikes() != null) {
            for (Like like : post.getLikes()) {
                like.setPostId(post.getId());
                likeDAO.update(like);
            }
        }
    }
    private void updatePostComments(Post post) throws SQLException {
        if (post.getComments() != null) {
            for (Comment comment : post.getComments()) {
                comment.setPostId(post.getId());
                commentDAO.update(comment);
            }
        }
    }
}

