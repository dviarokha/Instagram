package com.solvd.instagram.dao.mySQLIplm;

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
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Likes");
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Like like = resultSetToLike(rs);
                    likes.add(like);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return likes;
    }

    @Override
    public Like getLikesByLikedAt(LocalTime likedAt) throws SQLException {
        Like like = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Likes WHERE liked_at = ?");
        ) {
            stmt.setString(1, likedAt.toString());
            try (ResultSet  rs = stmt.executeQuery()) {
                while (rs.next()) {
                    like = resultSetToLike(rs);
                }
            }
        }  catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public Like getLikesByPostId(Long postId) throws SQLException {
        Like like = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Likes WHERE post_id = ?");
        ) {
            stmt.setLong(1, postId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                like = resultSetToLike(rs);
            }
        }
        }   catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public Like getLikesByUserId(Long userId) throws SQLException {
        Like like = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Likes WHERE user_id = ?");
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                like = resultSetToLike(rs);
            }
            }
        }    catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return like;
    }

    @Override
    public Like insert(Like entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Likes(liked_at, post_id, user_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getUserId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
            try (ResultSet  rs = stmt.getGeneratedKeys()) {
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
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Likes WHERE like_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    like = resultSetToLike(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public Like update(Like entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Likes SET liked_at = ?, post_id = ? , user_id = ?  WHERE like_id = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Likes WHERE like_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.executeUpdate();
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
