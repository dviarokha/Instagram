package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowMapper {
    void update(@Param("id") Long id, @Param("followerId") Long followerId, @Param("followedId") Long followedId);
    void delete(@Param("id") Long id);
    List<Follow> getFollowers(@Param("followerId") Long followerId);
    List<Follow> getFollowings(@Param("followerId") Long followerId);
    Follow getFollow(@Param("followedId") Long followedId, @Param("followerId") Long followerId);
    int countFollowers(@Param("followerId") Long followerId);
}
