package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Post;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostMapper {
    void createPost(Post post);
    void deletePostById(@Param("id") int id);
    void updatePost(Post post);
    Post findPost(@Param("posted_at")LocalDateTime postedAt, @Param("post_type_id") Long postTypeId ,@Param("user_id") Long userId);
    List<Post> findAllPosts();
    Post findByTypeAndUserId(@Param("post_type_id") Long postTypeId, @Param("user_id") Long userId);

}
