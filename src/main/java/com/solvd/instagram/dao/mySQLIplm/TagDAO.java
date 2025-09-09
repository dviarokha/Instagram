package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ITagDAO;
import com.solvd.instagram.models.Post;
import com.solvd.instagram.models.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAO extends MySQL implements ITagDAO<Tag> {
    private static final Logger logger = LogManager.getLogger(TagDAO.class);

    @Override
    public List getAllTags() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM Tags";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tags.add(resultSetToTag(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return tags;
    }

    @Override
    public Tag findByName(String name) throws SQLException {
        Tag tag = null;
        String sql = "SELECT * FROM Tags WHERE tag_name = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tag = resultSetToTag(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return tag;
    }

    @Override
    public Tag insert(Tag entity) throws SQLException {
        String sql = "INSERT INTO Tags (tag_name) VALUES (?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, entity.getTagName());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert row into the table");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while inserting tag ", e);
            throw e;
        }
        return entity;
    }

    @Override
    public Tag getById(Long id) throws SQLException {
        Tag tag = null;
        String sql = "SELECT * FROM Tags WHERE tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tag = resultSetToTag(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error while getting tag ", e);
            throw e;
        }
        return tag;
    }

    @Override
    public Tag update(Tag entity) throws SQLException {
        String sql = "UPDATE Tags SET tag_name = ? WHERE tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getTagName());
            stmt.setLong(2, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                logger.warn("Error when updating tag by ID:" + entity.getId());
            }
        } catch (SQLException e) {
            logger.error("Error while updating tag ", e);
            throw e;
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Tags WHERE tag_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                logger.warn("Error when deleting tag by ID:" + id);
            }
        } catch (SQLException e) {
            logger.error("Error while removing tag ", e);
        }
    }

    private Tag resultSetToTag(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getLong("id"));
        tag.setTagName(rs.getString("tag_name"));
        return tag;
    }
}
