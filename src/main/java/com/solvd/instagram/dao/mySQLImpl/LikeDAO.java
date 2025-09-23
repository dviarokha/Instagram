package com.solvd.instagram.dao.mySQLImpl;

import com.solvd.instagram.dao.ILikeDAO;
import com.solvd.instagram.models.Like;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO extends MySQL implements ILikeDAO<Like> {
    private static final Logger logger = LogManager.getLogger(LikeDAO.class);

    @Override
    public List<Like> getAllLikes() throws SQLException {
        List<Like> likes = new ArrayList<>();
        String sql = "SELECT * FROM Likes";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    likes.add(resultSetToLike(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return likes;
    }

    @Override
    public Like findByLikedAt(LocalTime likedAt) throws SQLException {
        Like like = null;
        String sql = "SELECT * FROM Likes WHERE liked_at = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, likedAt.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    like = resultSetToLike(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public List<Like> findByPostId(Long postId) throws SQLException {
        List<Like> likes = new ArrayList<>();
        String sql = "SELECT * FROM Likes WHERE post_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, postId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    likes.add(resultSetToLike(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return likes;
    }

    @Override
    public Like findByUserId(Long userId) throws SQLException {
        Like like = null;
        String sql = "SELECT * FROM Likes WHERE user_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    like = resultSetToLike(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public Like insert(Like entity) throws SQLException {
        String sql = "INSERT INTO Likes(liked_at, post_id, user_id) VALUES (?,?,?)";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getUserId());
            stmt.setLong(3, entity.getId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Insert failed. No rows affected.");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Like getById(Long id) throws SQLException {
        Like like = null;
        String sql = "SELECT * FROM Likes WHERE like_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    like = resultSetToLike(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public Like update(Like entity) throws SQLException {
        String sql = "UPDATE Likes SET liked_at = ?, post_id = ? , user_id = ?  WHERE like_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed. No rows affected.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Likes WHERE like_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
               int rowsDeleted = stmt.executeUpdate();
               if (rowsDeleted == 0) {
                   throw new SQLException("Delete failed. No rows affected.");
               }
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw e;
            }
        }
    }

    private Like resultSetToLike(ResultSet rs) throws SQLException {
        Like like = new Like();
        like.setId(rs.getLong("id"));
        like.setUserId(rs.getLong("user_id"));
        like.setPostId(rs.getLong("post_id"));
        return like;
    }
}
