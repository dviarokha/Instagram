package com.solvd.instagram.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ISupportRequestDAO<T> extends  IBaseDAO<T>  {
    List<T> getAllSupportRequest() throws SQLException;
    T getSupportRequestByName(String name) throws SQLException;
    T getSupportRequestByRequestDate(LocalDate date) throws SQLException;
    List<T> getSupportRequestByUserId(Long userId) throws SQLException;
}
