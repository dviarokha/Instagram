package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
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
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Posts");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getLong("id"));
                post.setUserId(rs.getLong("user_id"));
                post.setPostedAt(LocalDateTime.parse(rs.getString("posted_at")));
                post.setPostTypeId(rs.getLong("post_type_id"));
                post.setUserId(rs.getLong("user_id"));
                posts.add(post);
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
            if (c != null) {
                c.close();
            }
        }
        return posts;
    }

    @Override
    public Post getPostByPostedAt(LocalDateTime postedAt) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Posts WHERE posted_at = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(postedAt));
            rs = stmt.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getLong("id"));
                post.setUserId(rs.getLong("user_id"));
                post.setPostedAt(LocalDateTime.parse(rs.getString("posted_at")));
                post.setPostTypeId(rs.getLong("post_type_id"));
                post.setUserId(rs.getLong("user_id"));
            }
        }    catch (Exception e) {
            logger.error(e.getMessage());
        } finally  {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return post;
    }

    @Override
    public Post getPostByPostTypeId(long postTypeId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Posts WHERE post_type_id = ?");
            stmt.setLong(1, postTypeId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getLong("id"));
                post.setUserId(rs.getLong("user_id"));
                post.setPostedAt(LocalDateTime.parse(rs.getString("posted_at")));
                post.setPostTypeId(rs.getLong("post_type_id"));
                post.setUserId(rs.getLong("user_id"));
            }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }  finally  {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return post;
    }

    @Override
    public Post getPostByUserId(long userId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Posts WHERE user_id = ?");
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getLong("id"));
                post.setUserId(rs.getLong("user_id"));
                post.setPostedAt(LocalDateTime.parse(rs.getString("posted_at")));
                post.setPostTypeId(rs.getLong("post_type_id"));
                post.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }   finally  {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return post;
    }

    @Override
    public Post insert(Post entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Posts(posted_at, post_type_id, user_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());
            stmt.executeUpdate();
            while (rs.next()) {
                entity.setId(rs.getLong("id"));
            }
        } catch (Exception e) {
            logger.error(e);
        }  finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public Post getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Posts WHERE post_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                post.setId(rs.getLong("id"));
                post.setPostedAt(LocalDateTime.from(rs.getDate("posted_at").toLocalDate()));
                post.setPostTypeId(rs.getLong("post_type_id"));
                post.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            logger.error(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return post;
    }

    @Override
    public Post update(Post entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Posts SET posted_at = ?, post_type_id = ?, user_id = ? WHERE post_id = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getId());
            stmt.setLong(3, entity.getUserId());
            stmt.setLong(4, entity.getId());
            stmt.executeUpdate();
        }
        catch (Exception e) {
            logger.error(e);
        }  finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM Posts WHERE post_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        }  finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}
