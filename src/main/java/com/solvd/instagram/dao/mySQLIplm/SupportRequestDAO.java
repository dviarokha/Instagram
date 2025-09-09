package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ISupportRequestDAO;

import com.solvd.instagram.models.Post;
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
        List<SupportRequest> supportRequests = new ArrayList<>();
        String sql = "SELECT * FROM SupportRequest";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    supportRequests.add(resultSetToSupportRequest(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return supportRequests;
    }

    @Override
    public SupportRequest findByName(String name) throws SQLException {
        SupportRequest supportRequest = new SupportRequest();
        String sql = "SELECT * FROM SupportRequest WHERE request_name = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    supportRequest = resultSetToSupportRequest(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return supportRequest;
    }

    @Override
    public SupportRequest findByRequestDate(LocalDate date) throws SQLException {
        SupportRequest supportRequest = new SupportRequest();
        String sql = "SELECT * FROM SupportRequest WHERE request_date = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, date.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultSetToSupportRequest(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return supportRequest;
    }

    @Override
    public List<SupportRequest> findByUserId(Long userId) throws SQLException {
        List<SupportRequest> supportRequests = new ArrayList<>();
        String sql = "SELECT * FROM SupportRequest WHERE user_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    supportRequests.add(resultSetToSupportRequest(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return supportRequests;
    }

    @Override
    public SupportRequest insert(SupportRequest entity) throws SQLException {
        String sql = "INSERT INTO Support_Requests(request_name, request_date) VALUES (?,?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, entity.getRequestName());
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 0) {
                throw new SQLException("Failed to insert row into the table");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public SupportRequest getById(Long id) throws SQLException {
        SupportRequest supportRequest = null;
        String sql = "SELECT * FROM SupportRequest WHERE support_request_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    supportRequest = resultSetToSupportRequest(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return supportRequest;
    }

    @Override
    public SupportRequest update(SupportRequest entity) throws SQLException {
        String sql = "UPDATE Support_Requests SET request_name = ?, request_date = ? WHERE suppor_request_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getRequestName());
            stmt.setDate(2, Date.valueOf(entity.getRequestDate()));
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update row into the table");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM SupportRequest WHERE support_request_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowDeleted = stmt.executeUpdate();
            if (rowDeleted == 0) {
                logger.warn("Failed to delete row from the table");
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private SupportRequest resultSetToSupportRequest(ResultSet rs) throws SQLException {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId(rs.getLong("id"));
        supportRequest.setRequestName(rs.getString("request_name"));
        supportRequest.setRequestDate(rs.getDate("request_date").toLocalDate());
        return supportRequest;
    }


}


