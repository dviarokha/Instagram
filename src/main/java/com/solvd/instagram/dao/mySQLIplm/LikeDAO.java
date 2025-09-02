package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
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
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Like> likes = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Likes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Like like = new Like();
                like.setId(rs.getLong("id"));
                like.setLikedAt(LocalTime.parse(rs.getString("liked_at")));
                like.setUserId(rs.getLong("user_id"));
                like.setPostId(rs.getLong("post_id"));
                likes.add(like);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (stmt != null) {
            stmt.close();
        }
        if (connection != null) {
            connection.close();
        }
        return likes;
    }

    @Override
    public Like getLikesByLikedAt(LocalTime likedAt) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Like like = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Likes WHERE liked_at = ?");
            stmt.setString(1, likedAt.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                like = new Like();
                like.setId(rs.getLong("id"));
                like.setLikedAt(LocalTime.parse(rs.getString("liked_at")));
                like.setUserId(rs.getLong("user_id"));
                like.setPostId(rs.getLong("post_id"));
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
        }
        return like;
    }

    @Override
    public Like getLikesByPostId(Long postId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Like like = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Likes WHERE post_id = ?");
            stmt.setLong(1, postId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                like = new Like();
                like.setId(rs.getLong("id"));
                like.setLikedAt(LocalTime.parse(rs.getString("liked_at")));
                like.setUserId(rs.getLong("user_id"));
                like.setPostId(rs.getLong("post_id"));
            }
        }   catch (SQLException e) {
            logger.error(e.getMessage());
        }  finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return like;
    }

    @Override
    public Like getLikesByUserId(Long userId) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Like like = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Likes WHERE user_id = ?");
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                like = new Like();
                like.setId(rs.getLong("id"));
                like.setLikedAt(LocalTime.parse(rs.getString("liked_at")));
                like.setUserId(rs.getLong("user_id"));
                like.setPostId(rs.getLong("post_id"));
            }
        }    catch (SQLException e) {
            logger.error(e.getMessage());
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
        return like;
    }

    @Override
    public Like insert(Like entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("INSERT INTO Likes(liked_at, post_id, user_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getUserId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
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
    public Like getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Like like = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM Likes WHERE like_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                like = new Like();
                like.setId(id);
                like.setUserId(rs.getLong("user_id"));
                like.setPostId(rs.getLong("post_id"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
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
        return null;
    }

    @Override
    public Like update(Like entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = connection.prepareStatement("UPDATE Likes SET liked_at = ?, post_id = ? , user_id = ?  WHERE like_id = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
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
            stmt = connection.prepareStatement("DELETE FROM Likes WHERE like_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
