package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.IUserDAO;
import com.solvd.instagram.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserDAO extends MySQL implements IUserDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);


    @Override
    public void insert(User entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;

        String sql = "Insert into User (first_name, last_name, date_of_birth, email_address, phone_number ) " +
                "values (?, ?, ?, ?, ?)";

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram_model", "root", "");
            stmt = c.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress());
            stmt.setString(5, entity.getPhoneNumber());

            int i = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
                stmt.close();
                rs.close();
                c.close();
            //releaseConnection(c);
        }
    }

    @Override
    public User getById(Long userId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        User u = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            stmt.setLong(1,userId);
            stmt.executeQuery();
            while (rs.next()){
                u.setId(rs.getLong("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                u.setLastName(rs.getString("last_name"));
                u.setEmailAddress(rs.getString("email_address"));
                u.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            c.close();
            stmt.close();
            rs.close();
           //releaseConnection(c);
        }
        return u;
    }

    @Override
    public User update(User entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;

        String sql = "UPDATE Users SET first_name = ?, last_name = ?, date_of_birth = ?, email_address = ?, " +
                "phone_number = ? WHERE user_id = ? ";

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram_model", "root", "");
            stmt = c.prepareStatement(sql);

            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            stmt.setString(4, entity.getEmailAddress());
            stmt.setString(5, entity.getPhoneNumber());
            stmt.setLong(6, entity.getId());

            int i = stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        } finally {
            c.close();
            stmt.close();
            rs.close();
            //releaseConnection(c);
        }
        return null;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;

        String sql = "DELETE FROM users WHERE user_id = ?";


        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram_model", "root", "");
            stmt = c.prepareStatement(sql);
            stmt.setLong(1, id);

            int i = stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        } finally {
            c.close();
            stmt.close();
            rs.close();
            //releaseConnection(c);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserByEmail(String emailAddress) {
        return null;
    }
}
