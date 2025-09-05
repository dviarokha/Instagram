package com.solvd.instagram.dao.mySQLIplm;

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
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement("SELECT * FROM Posts");
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Post post = ResultSetToPost(rs);
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post getPostByPostedAt(LocalDateTime postedAt) throws SQLException {
        Post post = null;
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM Posts WHERE posted_at = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(postedAt));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    post = ResultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return post;
    }

    @Override
    public Post getPostByPostTypeId(long postTypeId) throws SQLException {
        Post post = null;
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM Posts WHERE post_type_id = ?");
        ) {
            stmt.setLong(1, postTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    post = ResultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return post;
    }

    @Override
    public Post getPostByUserId(long userId) throws SQLException {
        Post post = null;
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM Posts WHERE user_id = ?");
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    post = ResultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return post;
    }

    @Override
    public Post insert(Post entity) throws SQLException {;
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("INSERT INTO Posts(posted_at, post_type_id, user_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
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
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM Posts WHERE post_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    post = ResultSetToPost(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return post;
    }

    @Override
    public Post update(Post entity) throws SQLException {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("UPDATE Posts SET posted_at = ?, post_type_id = ?, user_id = ? WHERE post_id = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("DELETE FROM Posts WHERE post_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private Post ResultSetToPost(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setUserId(rs.getLong("user_id"));
        post.setPostedAt(LocalDateTime.from(rs.getDate("posted_at").toLocalDate()));
        post.setPostTypeId(rs.getLong("post_type_id"));
        return post;
    }
}
