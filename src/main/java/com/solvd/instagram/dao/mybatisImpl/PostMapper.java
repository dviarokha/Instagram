package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.models.Post;
import com.solvd.instagram.models.PostTag;

import java.time.LocalDateTime;
import java.util.List;

public interface PostMapper {
    void insert(Post post);
    void update(Post post);
    void deleteById(Long id);
    Post selectById(Long id);
    List<Post> getAll();
    Post findByPostedAt(LocalDateTime postedAt);
    List<Post> findByPostTypeId(long postTypeID);
    List<Post> findByUserId(long userID);

}
