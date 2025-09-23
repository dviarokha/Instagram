package com.solvd.instagram.dao.mySQLImpl;

import com.solvd.instagram.dao.IPostDAO;
import com.solvd.instagram.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends MySQL implements IPostDAO<Post> {
    private static final Logger logger = LogManager.getLogger(PostDAO.class);

    @Override
    public List<Post> getAllPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Profiles";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    posts.add(resultSetToPost(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post findByPostedAt(LocalDateTime postedAt) throws SQLException {
        Post post = null;
        String sql = "SELECT * FROM Profiles WHERE posted_at = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(postedAt));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    post = resultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return post;
    }

    @Override
    public List<Post> findByPostTypeId(long postTypeId) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Profiles WHERE post_type_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, postTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    posts.add(resultSetToPost(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public List<Post> findByUserId(long userId) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Profiles WHERE user_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    posts.add(resultSetToPost(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post insert(Post entity) throws SQLException {
        String sql = "INSERT INTO Posts(posted_at, post_type_id, user_id) VALUES (?,?,?)";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert rows into Posts");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entity.setId(rs.getLong("id"));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public Post getById(Long id) throws SQLException {
        Post post = null;
        String sql = "SELECT * FROM Posts WHERE post_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    post = resultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return post;
    }

    @Override
    public Post update(Post entity) throws SQLException {
        String sql = "UPDATE Posts SET posted_at = ?, post_type_id = ?, user_id = ? WHERE post_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update rows into Posts");
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Posts WHERE post_id = ?";
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
           int rowsDeleted = stmt.executeUpdate();
           if (rowsDeleted == 0) {
               throw new SQLException("Failed to update rows into Posts");
           }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private Post resultSetToPost(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setUserId(rs.getLong("user_id"));
        post.setPostedAt(rs.getTimestamp("posted_at").toLocalDateTime());
        post.setPostTypeId(rs.getLong("post_type_id"));
        return post;
    }
}
