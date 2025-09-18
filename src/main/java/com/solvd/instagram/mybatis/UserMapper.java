package com.solvd.instagram.mybatis;

import com.solvd.instagram.models.User;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserMapper {
    User getById(@Param("id") Long id);
    List<User> getUsers();
    User findByEmail(@Param("emailAddress") String email);
    User findByPhone(@Param("phoneNumber") String phone);
    User findByDateOfBirth(@Param("dateOfBirth") LocalDate dateOfBirth);
}
