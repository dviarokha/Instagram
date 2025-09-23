package com.solvd.instagram.dao.mySQLImpl;

import com.solvd.instagram.dao.IProfileDAO;
import com.solvd.instagram.models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO extends MySQL implements IProfileDAO<Profile> {
    private Logger logger = LogManager.getLogger(ProfileDAO.class);

    @Override
    public List<Profile> getAllProfiles() throws SQLException {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM Profile?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    profiles.add(resultSetToProfile(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return profiles;
    }

    @Override
    public Profile findByIsVerified(boolean isVerified) throws SQLException {
        String sql = "SELECT * FROM Profile WHERE is_verified = ?";
        Profile profile = null;
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, isVerified);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    profile = resultSetToProfile(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return profile;
    }

    @Override
    public Profile findByIsPrivate(boolean isPrivate) throws SQLException {
        String sql = "SELECT * FROM Profile WHERE is_private = ?";
        Profile profile = null;
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, isPrivate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    profile = resultSetToProfile(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return profile;
    }

    @Override
    public Profile findByProfileName(String profileName) throws SQLException {
        Profile profile = null;
        String sql = "SELECT * FROM Profile WHERE profile_name = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, profileName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    profile = resultSetToProfile(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return profile;
    }

    @Override
    public Profile insert(Profile entity) throws SQLException {
        String sql = "INSERT INTO Profiles(is_verified, is_private, profile_name) VALUES(?,?,?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setBoolean(1, entity.isVerified());
            stmt.setBoolean(2, entity.isPrivate());
            stmt.setString(3, entity.getProfileName());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Insert Profile failed");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Profile getById(Long id) throws SQLException {
        Profile profile = null;
        String sql = "SELECT * FROM Profile WHERE profile_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profile = resultSetToProfile(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return profile;
    }

    @Override
    public Profile update(Profile entity) throws SQLException {
        String sql = "UPDATE Profiles SET is_verified = ?, is_private =?, profile_name = ? WHERE profile_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, entity.isVerified());
            stmt.setBoolean(2, entity.isPrivate());
            stmt.setString(3, entity.getProfileName());
            stmt.setLong(4, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update Profile failed");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Profiles WHERE profile_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Delete Profile failed");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private Profile resultSetToProfile(ResultSet rs) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setVerified(rs.getBoolean("is_verified"));
        profile.setPrivate(rs.getBoolean("is_private"));
        profile.setProfileName(rs.getString("profile_name"));
        return profile;
    }
}
