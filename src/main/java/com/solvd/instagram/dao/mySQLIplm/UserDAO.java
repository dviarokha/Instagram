package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.IUserDAO;
import com.solvd.instagram.models.Post;
import com.solvd.instagram.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserDAO extends MySQL implements IUserDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    @Override
    public User insert(User entity) throws SQLException {
        String sql = "INSERT INTO Users (first_name, last_name, date_of_birth, email_address, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress("darya@gmail.com"));
            stmt.setString(5, entity.getPhoneNumber());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert row into the table");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while inserting user", e);
            throw e;
        }
        return entity;
    }

    @Override
    public User getById(Long userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = resultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error when getting user by ID:" + userId, e);
            throw e;
        }
        return user;
    }

    @Override
    public User update(User entity) throws SQLException {
        String sql = "UPDATE Users SET first_name = ?, last_name = ?, date_of_birth = ?, email_address = ?, phone_number = ? WHERE user_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress("darya@gmail.com"));
            stmt.setString(5, entity.getPhoneNumber());
            stmt.setLong(6, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                logger.warn("Error when updating user by ID:" + entity.getId());
            }
        } catch (SQLException e) {
            logger.error("Error updating user with id: " + entity.getId(), e);
            throw e;
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                logger.warn("No user found to delete with ID:" + id);
            }
        } catch (SQLException e) {
            logger.error("Error deleting user with id:" + id, e);
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Override
    public User findByEmail(String emailAddress) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM Users WHERE email_address = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, emailAddress);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = resultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting user by email:" + emailAddress, e);
            throw e;
        }
        return user;
    }

    @Override
    public User findByPhone(String phoneNumber) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM Users WHERE phone_number = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = resultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting user by phone:" + phoneNumber, e);
            throw e;
        }
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE first_name = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, firstName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting users by first name:" + firstName, e);
            throw e;
        }
        return users;
    }

    @Override
    public List<User> findByLastName(String lastName) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE last_name = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, lastName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting users by last name:" + lastName, e);
            throw e;
        }
        return users;
    }

    @Override
    public List<User> findByDateOfBirth(LocalDate dateOfBirth) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE date_of_birth = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, dateOfBirth.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting users by date of birth:" + dateOfBirth, e);
            throw e;
        }
        return users;
    }

    @Override
    public List<User> findByUserTypeId(Long userTypeId) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE user_type_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, userTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting users by user_type_id:" + userTypeId, e);
            throw e;
        }
        return users;
    }

    @Override
    public List<User> findByProfileId(Long profileId) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE profile_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, profileId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(resultSetToUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting users by profile_id:" + profileId, e);
            throw e;
        }
        return users;
    }

    private User resultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        user.setLastName(rs.getString("last_name"));
        user.setEmailAddress(rs.getString("email_address"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }
}
