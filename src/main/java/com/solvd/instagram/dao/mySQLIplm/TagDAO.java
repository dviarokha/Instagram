package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
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
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tag> tags = new ArrayList<>();

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Tags");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setTagName(rs.getString("tag_name"));
                tags.add(tag);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
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

        return List.of(tags);
    }

    @Override
    public Tag getTagByName(String name) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tag tag = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Tags WHERE tag_name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setTagName(rs.getString("tag_name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
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
        return tag;
    }

    @Override
    public Tag insert(Tag entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Tags (tag_name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.getTagName());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Error while inserting tag ", e);
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
        return entity;
    }
    
    @Override
    public Tag getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tag tag = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Tags WHERE tag_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setTagName(rs.getString("tag_name"));
            }
        } catch (SQLException e) {
            logger.error("Error while getting tag ", e);
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
        return tag;
    }

    @Override
    public Tag update(Tag entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Tags SET tag_name = ? WHERE tag_id = ?");
            stmt.setString(1, entity.getTagName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error while updating tag ", e);
        } finally {
            if (c != null) {
                c.close();
            }
            if (stmt != null) {
                stmt.close();
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
            stmt = c.prepareStatement("DELETE FROM Tags WHERE tag_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while removing tag ", e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}
