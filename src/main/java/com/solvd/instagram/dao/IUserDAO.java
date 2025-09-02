package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IUserDAO<T> extends IBaseDAO<T> {
    List<T> getAllUsers() throws SQLException;
    T getUserByEmail(String emailAddress) throws SQLException;
    T getUserByPhone(String phoneNumber) throws SQLException;
    List<T> getUsersByFirstName(String firstName) throws SQLException;
    List<T> getUsersByLastName(String lastName) throws SQLException;
    List<T> getUsersByDateOfBirth(LocalDate dateOfBirth) throws SQLException;
    List<T> getUsersByUserTypeId(Long userTypeId) throws SQLException;
    List<T> getUsersByProfileId(Long profileId) throws SQLException;

}
