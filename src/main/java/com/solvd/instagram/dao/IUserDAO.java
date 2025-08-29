package com.solvd.instagram.dao;

import com.solvd.instagram.models.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IUserDAO<User> extends IBaseDAO<User> {
    List<User> getAllUsers();
    User getUserByEmail(String emailAddress);
//    User getUserByPhone(String phoneNumber);
//    List<User> getUsersByFirstName(String firstName);
//    List<User> getUsersByLastName(String lastName);
//    List<User> getUsersByDateOfBirth(LocalDate dateOfBirth);
//    List<User> getUsersByUserTypeId(Long userTypeId);
//    List<User> getUsersByProfileId(Long profileId);
// void updateEmail(Long userId, String newEmail) throws SQLException;
// boolean existsByEmail(String email);
// int countUsersByUserType(Long userTypeId);
}
