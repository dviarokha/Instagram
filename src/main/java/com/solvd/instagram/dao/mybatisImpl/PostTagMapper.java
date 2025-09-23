package com.solvd.instagram.dao.mybatisImpl;

import com.solvd.instagram.models.PostTag;

import java.util.List;

public interface PostTagMapper {
    void insert(PostTag postTag);
    void update(PostTag postTag);
    void delete(long id);
    PostTag findById(long id);
    List<PostTag> getAll(long id);
    List<PostTag> findByPostId(long id);
    List<PostTag> findByTagId(long id);

}
