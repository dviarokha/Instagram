package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.IPostTagDAO;
import com.solvd.instagram.models.PostTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostTagDAO extends MySQL implements IPostTagDAO<PostTag> {
    public static final Logger logger = LogManager.getLogger(PostTagDAO.class);

    @Override
    public List<PostTag> getAllPostTags() throws SQLException {
        List<PostTag> postTags = new ArrayList<>();
        String sql = "SELECT * FROM PostTags";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    postTags.add(resultSetToPostTag(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return postTags;
    }

    @Override
    public List<PostTag> findByTagId(long id) throws SQLException {
        List<PostTag> postTags = new ArrayList<>();
        String sql = "SELECT * FROM PostTags WHERE tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    postTags.add(resultSetToPostTag(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return postTags;
    }

    @Override
    public List<PostTag> findByPostId(long id) throws SQLException {
        ArrayList<PostTag> postTags = new ArrayList<>();
        PostTag postTag = null;
        String sql = "SELECT * FROM PostTags WHERE post_id = ?";
        try(
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    postTag = resultSetToPostTag(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return postTags;
    }

    @Override
    public PostTag insert(PostTag entity) throws SQLException {
        String sql = "INSERT INTO PostTags VALUES (?, ?, ?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setLong(1, entity.getPostId());
            stmt.setLong(2, entity.getPostTypeId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Insert failed. No rows affected.");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public PostTag getById(Long id) throws SQLException {
        PostTag postTag = null;
        String sql = "SELECT * FROM PostTags WHERE post_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    postTag = resultSetToPostTag(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return postTag;
    }

    @Override
    public PostTag update(PostTag entity) throws SQLException {
        String sql = "UPDATE PostTags SET post_id = ?, tag_id = ? WHERE post_tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, entity.getPostId());
            stmt.setLong(2, entity.getPostTypeId());
            stmt.setLong(3, entity.getId());
           int rowsUpdated = stmt.executeUpdate();
           if (rowsUpdated == 0) {
               throw new SQLException("Update failed. No rows affected.");
           }
        } catch (Exception e) {
            logger.error(e);
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM PostTags WHERE post_tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Delete failed. No rows affected.");
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private PostTag resultSetToPostTag(ResultSet rs) throws SQLException {
        PostTag postTag = new PostTag();
        postTag.setId(rs.getLong("id_post"));
        postTag.setTagId(rs.getLong("id_tag"));
        postTag.setPostId(rs.getLong("id"));
        return postTag;
    }
}
