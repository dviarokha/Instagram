package com.solvd.instagram.dao;


import java.sql.SQLException;
import java.util.List;

public interface IProfileDAO<T> extends IBaseDAO<T> {
    List<T> getAllProfiles() throws SQLException;
    T getProfileByIsVerified(boolean isVerified) throws SQLException;
    T getProfileByIsPrivate(boolean isPrivate) throws SQLException;
    T getProfileByProfileName(String profileName) throws SQLException;

}
