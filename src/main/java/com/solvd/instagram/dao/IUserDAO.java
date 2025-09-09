package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IUserDAO<T> extends IBaseDAO<T> {
    List<T> getAllUsers() throws SQLException;
    T findByEmail(String emailAddress) throws SQLException;
    T findByPhone(String phoneNumber) throws SQLException;
    List<T> findByFirstName(String firstName) throws SQLException;
    List<T> findByLastName(String lastName) throws SQLException;
    List<T> findByDateOfBirth(LocalDate dateOfBirth) throws SQLException;
    List<T> findByUserTypeId(Long userTypeId) throws SQLException;
    List<T> findByProfileId(Long profileId) throws SQLException;

}
