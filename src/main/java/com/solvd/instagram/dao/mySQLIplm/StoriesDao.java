package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.bd.MySQL;
import com.solvd.instagram.dao.IStoriesDAO;
import com.solvd.instagram.models.Stories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoriesDao extends MySQL implements IStoriesDAO<Stories> {
    private static final Logger LOGGER = LogManager.getLogger(StoriesDao.class);

    @Override
    public List getAllStories() throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Stories> stories = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Stories");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Stories story = new Stories();
                story.setId(rs.getLong("id"));
                story.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                story.setExpiredAt(LocalDateTime.parse(rs.getString("expired_at")));
                story.setUserId(rs.getLong("user_id"));
                stories.add(story);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
        return List.of((Stories) stories) ;
    }

    @Override
    public Stories getStoriesByCreatedDate(LocalDateTime createdDate) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Stories stories = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Stories WHERE created_at = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(createdDate));
            rs = stmt.executeQuery();
            while (rs.next()) {
                Stories story = new Stories();
                story.setId(rs.getLong("id"));
                story.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                story.setExpiredAt(LocalDateTime.parse(rs.getString("expired_at")));
                story.setUserId(rs.getLong("user_id"));
            }
        } catch (Exception e) {
        LOGGER.error(e.getMessage());
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
        return stories ;
    }

    @Override
    public Stories getStoriesByExpiredDate(LocalDateTime expiredDate) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Stories stories = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Stories WHERE expired_at = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(expiredDate));
            rs = stmt.executeQuery();
            while (rs.next()) {
                Stories story = new Stories();
                story.setId(rs.getLong("id"));
                story.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                story.setExpiredAt(LocalDateTime.parse(rs.getString("expired_at")));
                story.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
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
        return stories;
    }

    @Override
    public List getAllStoriesByUserId(Long userId) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Stories> stories = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Stories WHERE user_id = ?");
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Stories story = new Stories();
                story.setId(rs.getLong("id"));
                story.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));
                story.setExpiredAt(LocalDateTime.parse(rs.getString("expired_at")));
                story.setUserId(rs.getLong("user_id"));
            }
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
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
        return stories;
    }

    @Override
    public Stories insert(Stories entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO Stories(created_at, expired_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        }  catch (SQLException e) {
            LOGGER.error(e.getMessage());
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
        return entity;
    }

    @Override
    public Stories getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Stories stories = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Stories WHERE stories_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                stories = new Stories();
                stories.setId(rs.getLong("id"));
                stories.setCreatedAt(LocalDateTime.from(rs.getDate("created_at").toLocalDate()));
                stories.setExpiredAt(LocalDateTime.from(rs.getDate("expired_at").toLocalDate()));
                stories.setUserId(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
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
        return stories;
    }

    @Override
    public Stories update(Stories entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE Stories Set created_at = ?, expired_at = ? WHERE stories_id = ?");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, entity.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
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
            stmt = c.prepareStatement("DELETE FROM Stories WHERE stroies_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
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
