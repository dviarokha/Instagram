package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.IUserDAO;
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
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Users (first_name, last_name, date_of_birth, email_address, phone_number ) " +
                    "VALUES (?, ?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress());
            stmt.setString(5, entity.getPhoneNumber());
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            logger.error("Error while inserting user", e);
        } finally {
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
            if (c != null) {c.close();}
            //releaseConnection(c);
        }
        return entity;
    }

    @Override
    public User getById(Long userId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        User user = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");

            stmt = c.prepareStatement("SELECT * FROM Users WHERE user_id = ?");
            stmt.setLong(1,userId);

            rs = stmt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error("Error when getting user by ID:" + userId, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
            //releaseConnection(c);
        }
        return user;
    }

    @Override
    public User update(User entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Users SET first_name = ?, last_name = ?, date_of_birth = ?, email_address = ?, " +
                    "phone_number = ? WHERE user_id = ? ");

            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress());
            stmt.setString(5, entity.getPhoneNumber());
            stmt.setLong(6, entity.getId());

            int i = stmt.executeUpdate();
            if (i == 0) {
                throw new SQLException("The user was not updated in the database.");
            }

        } catch (SQLException e) {
            logger.error("Error updating user with id: " + entity.getId(), e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            //releaseConnection(c);
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM Users WHERE user_id = ?");
            stmt.setLong(1, id);

            int i = stmt.executeUpdate();
            if (i == 0) {
                throw new SQLException("The user was not removed from the database.");
            }

        } catch (SQLException e) {
            logger.error("Error deleting user with id:" + id , e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            //releaseConnection(c);
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users");
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
                users.add(user);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }

    @Override
    public User getUserByEmail(String emailAddress) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        User user = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE email_address = ?");
            stmt.setString(1, emailAddress);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error("Error while getting user by email:" + emailAddress, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return user;
    }

    @Override
    public User getUserByPhone(String phoneNumber) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        User user = null;

        try {
            c = c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE phone_number = ?");
            stmt.setString(1, phoneNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error("Error while getting user by phone:" + phoneNumber, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return user;
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE first_name = ?");
            stmt.setString(1, firstName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error("Error while getting users by first name:" + firstName, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }

    @Override
    public List<User> getUsersByLastName(String lastName) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE last_name = ?");
            stmt.setString(1, lastName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        }  catch (Exception e) {
            logger.error("Error while getting users by last name:" + lastName, e);
        }  finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }

    @Override
    public List<User> getUsersByDateOfBirth(LocalDate dateOfBirth) throws SQLException{
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE date_of_birth = ?");
            stmt.setString(1, dateOfBirth.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error("Error while getting users by date of birth:" + dateOfBirth, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }

    @Override
    public List<User> getUsersByUserTypeId(Long userTypeId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE user_type_id = ?");
            stmt.setLong(1, userTypeId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setUserTypeId(userTypeId);
            }
        } catch (Exception e) {
            logger.error("Error while getting users by user_type_id:" + userTypeId, e);
        } finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }

    @Override
    public List<User> getUsersByProfileId(Long profileId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<User> users = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Users WHERE profile_id = ?");
            stmt.setLong(1, profileId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user.setLastName(rs.getString("last_name"));
                user.setEmailAddress(rs.getString("email_address"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        }  catch (Exception e) {
            logger.error("Error while getting users by profile_id:" + profileId, e);
        }  finally {
            if (c != null) {c.close();}
            if (stmt != null) {stmt.close();}
            if (rs != null) {rs.close();}
        }
        return users;
    }
}
