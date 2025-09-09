package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.IStoriesDAO;
import com.solvd.instagram.models.Post;
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
        List<Stories> stories = new ArrayList<>();
        String sql = "SELECT * FROM Stories";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    stories.add(resultSetToStories(rs));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return stories;
    }

    @Override
    public Stories findByCreatedDate(LocalDateTime createdDate) throws SQLException {
        Stories stories = null;
        String sql = "SELECT * FROM Stories WHERE created_date = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(createdDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    stories = resultSetToStories(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return stories;
    }

    @Override
    public Stories findByExpiredDate(LocalDateTime expiredDate) throws SQLException {
        Stories stories = null;
        String sql = "SELECT * FROM Stories WHERE expired_date = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(expiredDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    stories = resultSetToStories(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        return stories;
    }

    @Override
    public Stories findByUserId(Long userId) throws SQLException {
        Stories stories = null;
        String sql = "SELECT * FROM Stories WHERE user_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    stories = resultSetToStories(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return stories;
    }

    @Override
    public Stories insert(Stories entity) throws SQLException {
        String sql = "INSERT INTO Stories(created_at, expired_at) VALUES (?, ?)";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Create Stories failed, no rows inserted.");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Stories getById(Long id) throws SQLException {
        Stories stories = null;
        String sql = "SELECT * FROM Stories WHERE stroies_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stories = resultSetToStories(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return stories;
    }

    @Override
    public Stories update(Stories entity) throws SQLException {
        String sql = "UPDATE Stories Set created_at = ?, expired_at = ? WHERE stories_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, entity.getId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Update Stories failed, no rows inserted.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Stories WHERE stroies_id = ?";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted != 0) {
                throw new SQLException("Delete Stories failed, no rows deleted.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Stories resultSetToStories(ResultSet rs) throws SQLException {
        Stories stories = new Stories();
        stories.setId(rs.getLong("id"));
        stories.setCreatedAt(LocalDateTime.from(rs.getDate("created_at").toLocalDate()));
        stories.setExpiredAt(LocalDateTime.from(rs.getDate("expired_at").toLocalDate()));
        stories.setUserId(rs.getLong("user_id"));
        return stories;
    }
}
