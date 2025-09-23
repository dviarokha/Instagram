package com.solvd.instagram.dao.mySQLImpl;

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
        List<Follow> followers = new ArrayList<>();
        String sql = "SELECT * FROM Followers";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    followers.add(resultSetToFollow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return followers;
    }

    @Override
    public Follow findByFollowerID(long id) throws SQLException {
        Follow follow = null;
        String sql = "SELECT * FROM Follows WHERE follower_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    follow = resultSetToFollow(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return follow;
    }

    @Override
    public Follow findByFollowedID(long id) throws SQLException {
        Follow follow = null;
        String sql = "SELECT * FROM Follows WHERE followed_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    follow = resultSetToFollow(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return follow;
    }

    @Override
    public Follow insert(Follow entity) throws SQLException {
        String sql = "INSERT INTO Follows(followed_id, follower_id) VALUES(?, ?)";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert row.");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return entity;
        }
    }

    @Override
    public Follow getById(Long id) throws SQLException {
        Follow follow = null;
        String sql = "SELECT * FROM Follows WHERE follow_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    follow = resultSetToFollow(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return follow;
    }

    @Override
    public Follow update(Follow entity) throws SQLException {
        String sql = "UPDATE Follows SET follower_id, followed_id = ? WHERE follow_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getId());
           int rowsUndated = stmt.executeUpdate();
           if (rowsUndated == 0) {
               throw new SQLException("Failed to update row.");
           }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Follows WHERE follow_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
           int rowsDeleted = stmt.executeUpdate();
           if (rowsDeleted == 0) {
               throw new SQLException("Failed to delete row.");
           }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Follow resultSetToFollow(ResultSet rs) throws SQLException {
        Follow follow = new Follow();
        follow.setId(rs.getLong("id"));
        follow.setFollowerId(rs.getLong("follower_id"));
        follow.setFollowedId(rs.getLong("followed_id"));
        return follow;
    }
}



