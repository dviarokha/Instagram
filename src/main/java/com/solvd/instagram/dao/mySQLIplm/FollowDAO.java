package com.solvd.instagram.dao.mySQLIplm;

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
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Follows");
        ) {
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Follow follow = resultSetToFollow(rs);
                    followers.add(follow);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return followers;
    }

    @Override
    public Follow getFollowsByFollowerID(long id) throws SQLException {
        Follow follow = null;
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Follows WHERE follower_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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
    public Follow getFollowsByFollowedID(long id) throws SQLException {
        Follow follow = null;
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Follows WHERE followed_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO Follows (follower_id, followed_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            stmt.executeUpdate();
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
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Follows WHERE follow_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
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
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("UPDATE Follows SET follower_id, followed_id = ? WHERE follow_id = ?");
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Follows WHERE follow_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
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



