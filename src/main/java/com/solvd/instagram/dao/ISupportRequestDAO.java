package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ISupportRequestDAO<T> extends  IBaseDAO<T>  {
    List<T> getAllSupportRequest() throws SQLException;
    T findByName(String name) throws SQLException;
    T findByRequestDate(LocalDate date) throws SQLException;
    List<T> findByUserId(Long userId) throws SQLException;
}
