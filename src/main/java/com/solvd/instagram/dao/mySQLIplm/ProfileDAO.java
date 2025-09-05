package com.solvd.instagram.dao.mySQLIplm;

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
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM Profiles");
            ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Profile profile = resultSetToProfile(rs);
                    profiles.add(profile);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return profiles;
    }

    @Override
    public Profile getProfileByIsVerified(boolean isVerified) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profile profile = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Profiles WHERE is_verified = ?");
            stmt.setBoolean(1, isVerified);
            rs = stmt.executeQuery();
            while (rs.next()) {
                profile = new Profile();
                profile.setId(rs.getLong("id"));
                profile.setVerified(rs.getBoolean("is_verified"));
                profile.setPrivate(rs.getBoolean("is_private"));
                profile.setProfileName(rs.getString("profile_name"));
            }
        }  catch (SQLException e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return profile;
    }

    @Override
    public Profile getProfileByIsPrivate(boolean isPrivate) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profile profile = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Profiles WHERE is_private = ?");
            stmt.setBoolean(1, isPrivate);
            rs = stmt.executeQuery();
            while (rs.next()) {
                profile = new Profile();
                profile.setId(rs.getLong("id"));
                profile.setVerified(rs.getBoolean("is_verified"));
                profile.setPrivate(rs.getBoolean("is_private"));
                profile.setProfileName(rs.getString("profile_name"));
            }
        }   catch (SQLException e) {
            logger.error(e.getMessage());
        } finally  {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return profile;
    }

    @Override
    public Profile getProfileByProfileName(String profileName) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profile profile = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Profiles WHERE profile_name = ?");
            stmt.setString(1, profileName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                profile = new Profile();
                profile.setId(rs.getLong("id"));
                profile.setVerified(rs.getBoolean("is_verified"));
                profile.setPrivate(rs.getBoolean("is_private"));
            }
        }    catch (SQLException e) {
            logger.error(e.getMessage());
        }  finally  {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return profile;
    }

    @Override
    public Profile insert(Profile entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Profiles(is_verified, is_private, profile_name) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, entity.isVerified());
            stmt.setBoolean(2, entity.isPrivate());
            stmt.setString(3, entity.getProfileName());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }  catch (SQLException e) {
            logger.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public Profile getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profile profile = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Profiles WHERE profile_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                profile = new Profile();
                profile.setId(id);
                profile.setVerified(rs.getBoolean(1));
                profile.setPrivate(rs.getBoolean(2));
                profile.setProfileName(rs.getString(3));
            }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return profile;
    }

    @Override
    public Profile update(Profile entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Profiles SET is_verified = ?, is_private =?, profile_name = ? WHERE profile_id = ?");
            stmt.setBoolean(1, entity.isVerified());
            stmt.setBoolean(2, entity.isPrivate());
            stmt.setString(3, entity.getProfileName());
            stmt.setLong(4, entity.getId());
            stmt.executeUpdate();
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }    finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM Profiles WHERE profile_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }   catch (SQLException e) {
            logger.error(e.getMessage());
        }    finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
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
