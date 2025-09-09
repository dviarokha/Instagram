package com.solvd.instagram.dao;


import java.sql.SQLException;
import java.util.List;

public interface IProfileDAO<T> extends IBaseDAO<T> {
    List<T> getAllProfiles() throws SQLException;
    T findByIsVerified(boolean isVerified) throws SQLException;
    T findByIsPrivate(boolean isPrivate) throws SQLException;
    T findByProfileName(String profileName) throws SQLException;
}
