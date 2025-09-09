package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ICommentDAO;

import com.solvd.instagram.models.Comment;
import com.solvd.instagram.models.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends MySQL implements ICommentDAO<Comment> {
    private static final Logger logger = LogManager.getLogger(CommentDAO.class);

    @Override
    public List<Comment> getAllComments() throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM Comments";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comments.add(resultSetToComment(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comments;
    }

    @Override
    public Comment findByCommentedAt(LocalDateTime commentedAt) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM Comments WHERE commented_at = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, commentedAt.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment findByTextComment(String textComment) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM Comments WHERE text_comment = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, textComment);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public List<Comment> findByPostId(long postId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM Comments WHERE post_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, postId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comments.add(resultSetToComment(rs));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comments;
    }

    @Override
    public List<Comment> findByUserId(long userId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM Comments WHERE user_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comments.add(resultSetToComment(rs));
                }
            }
            catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return comments;
    }

    @Override
    public Comment insert(Comment entity) throws SQLException {
        String sql = "INSERT INTO Comments (commented_at, post_id, text_commented, user_id) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setString(3, entity.getTextComment());
            stmt.setLong(4, entity.getUserId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert row into the table");
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
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
    public Comment getById(Long id) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM Comments WHERE comment_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment update(Comment entity) throws SQLException {
        String sql = "UPDATE Comments SET commented_at = ?, post_id = ?, text_comment = ?, user_id = ? WHERE comment_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setString(3, entity.getTextComment());
            stmt.setLong(4, entity.getUserId());
            stmt.setLong(5, entity.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update row into the table");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String sql = "DELETE FROM Comments WHERE comment_id = ?";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Failed to delete row from the table");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    private Comment resultSetToComment(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setUserId(rs.getLong("user_id"));
        comment.setPostId(rs.getLong("post_id"));
        comment.setTextComment(rs.getString("text_commented"));
        comment.setCommentedAt(LocalDateTime.parse(rs.getString("commented_at")));
        return comment;
    }
}
