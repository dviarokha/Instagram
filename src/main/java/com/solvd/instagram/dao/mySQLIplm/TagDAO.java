package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ITagDAO;
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
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement("SELECT * FROM Tags");
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tag tag = resultSetToTag(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return tags;
    }

    @Override
    public Tag getTagByName(String name) throws SQLException {
        Tag tag = null;
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement("SELECT * FROM Tags WHERE tag_name = ?");
        ) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    tag = resultSetToTag(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return tag;
    }

    @Override
    public Tag insert(Tag entity) throws SQLException {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("INSERT INTO Tags (tag_name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, entity.getTagName());
            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()) {

                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Error while inserting tag ", e);
        }
        return entity;
    }

    @Override
    public Tag getById(Long id) throws SQLException {
        Tag tag = null;
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement  stmt = c.prepareStatement("SELECT * FROM Tags WHERE tag_id = ?");
        ) {
            stmt.setLong(1, id);
           try(ResultSet rs = stmt.executeQuery()) {
               while (rs.next()) {
                   tag = resultSetToTag(rs);
               }
           }
        } catch (SQLException e) {
            logger.error("Error while getting tag ", e);
        }
        return tag;
    }

    @Override
    public Tag update(Tag entity) throws SQLException {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = c.prepareStatement("UPDATE Tags SET tag_name = ? WHERE tag_id = ?");
        ) {
            stmt.setString(1, entity.getTagName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while updating tag ", e);
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement  stmt = c.prepareStatement("DELETE FROM Tags WHERE tag_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
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
