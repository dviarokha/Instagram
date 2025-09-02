package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.IFollowDAO;
import com.solvd.instagram.models.Follow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FollowDAO extends MySQL implements IFollowDAO<Follow> {
    private static final Logger LOGGER = LogManager.getLogger(FollowDAO.class);


    @Override
    public List<Follow> getAllFollowers() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Follow> followers = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Follows");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Follow follow = new Follow();
                follow.setId(rs.getLong(1));
                follow.setFollowerId(rs.getLong(2));
                follow.setFollowedId(rs.getLong(3));
                followers.add(follow);
            }
        }  catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return followers;
    }

    @Override
    public Follow getFollowsByFollowerID(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Follow follow = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Follows WHERE follower_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                follow = new Follow();
                follow.setId(rs.getLong(1));
                follow.setFollowerId(rs.getLong(2));
                follow.setFollowedId(rs.getLong(3));
            }
        }    catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return follow;
    }

    @Override
    public Follow getFollowsByFollowedID(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Follow follow = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                stmt = connection.prepareStatement("SELECT * FROM Follows WHERE followed_id = ?");
                stmt.setLong(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    follow = new Follow();
                    follow.setId(rs.getLong(1));
                    follow.setFollowerId(rs.getLong(2));
                    follow.setFollowedId(rs.getLong(3));
                }
            }   catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }   finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        return follow;
    }

    @Override
    public Follow insert(Follow entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("INSERT INTO Follows (follower_id, followed_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return entity;
    }

    @Override
    public Follow getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Follow follow = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Follows WHERE follow_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                follow = new Follow();
                follow.setId(id);
                follow.setFollowerId(rs.getLong(1));
                follow.setFollowedId(rs.getLong(2));
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return follow;
    }

    @Override
    public Follow update(Follow entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("UPDATE Follows SET follower_id, followed_id = ? WHERE follow_id = ?");
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("DELETE FROM Follows WHERE follow_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }   catch (Exception e) {
            LOGGER.error(e.getMessage());
        }   finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
