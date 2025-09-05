package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ISupportRequestDAO;

import com.solvd.instagram.models.SupportRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SupportRequestDAO extends MySQL implements ISupportRequestDAO<SupportRequest> {
    public static final Logger logger = LogManager.getLogger(SupportRequestDAO.class);


    @Override
    public List<SupportRequest> getAllSupportRequest() throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<SupportRequest> supportRequests = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Support_requests");
            rs = stmt.executeQuery();
            while (rs.next()) {
                SupportRequest supportRequest = new SupportRequest();
                supportRequest.setId(rs.getLong("id"));
                supportRequest.setRequestName(rs.getString("request_name"));
                supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
                supportRequest.setUserId(rs.getLong("user_id"));
                supportRequests.add(supportRequest);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return supportRequests;
    }

    @Override
    public SupportRequest getSupportRequestByName(String name) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SupportRequest supportRequest = new SupportRequest();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Support_requests WHERE request_name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                supportRequest.setId(rs.getLong("id"));
                supportRequest.setRequestName(rs.getString("request_name"));
                supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
                supportRequest.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return supportRequest;
    }

    @Override
    public SupportRequest getSupportRequestByRequestDate(LocalDate date) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SupportRequest supportRequest = new SupportRequest();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Support_requests WHERE request_date = ?");
            stmt.setString(1, date.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                supportRequest.setId(rs.getLong("id"));
                supportRequest.setRequestName(rs.getString("request_name"));
                supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
                supportRequest.setUserId(rs.getLong("user_id"));
            }
        } catch (Exception e) {
            logger.error(e);
        }  finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return supportRequest;
    }

    @Override
    public List<SupportRequest> getSupportRequestByUserId(Long userId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<SupportRequest> supportRequests = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Support_requests WHERE user_id = ?");
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SupportRequest supportRequest = new SupportRequest();
                supportRequest.setId(rs.getLong("id"));
                supportRequest.setRequestName(rs.getString("request_name"));
                supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
                supportRequest.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e);
        }  finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return supportRequests;
    }

    @Override
    public SupportRequest insert(SupportRequest entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Support_Requests(request_name, request_date) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.getRequestName());
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return entity;
    }

    @Override
    public SupportRequest getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SupportRequest supportRequest = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Support_Requests WHERE suppor_request_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                supportRequest = new SupportRequest();
                supportRequest.setId(rs.getLong("id"));
                supportRequest.setRequestName(rs.getString("request_name"));
                supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return supportRequest;
    }

    @Override
    public SupportRequest update(SupportRequest entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt =  c.prepareStatement("UPDATE Support_Requests SET request_name = ?, request_date = ? WHERE suppor_request_id = ?");
            stmt.setString(1, entity.getRequestName());
            stmt.setDate(2, Date.valueOf(entity.getRequestDate()));
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM Support_Requests WHERE suppor_request_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }  finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}


