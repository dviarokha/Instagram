package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.ICommentDAO;

import com.solvd.instagram.models.Comment;
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
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Comments");
        ) {
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Comment comment = resultSetToComment(rs);
                    comments.add(comment);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comments;
    }

    @Override
    public Comment getCommentsByCommentedAt(LocalDateTime commentedAt) throws SQLException {
        Comment comment = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Comments WHERE commented_at = ?");
        ) {
            stmt.setString(1, commentedAt.toString());
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment getCommentsByTextComment(String textComment) throws SQLException {
        Comment comment = null;
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Comments WHERE text_comment = ?");
        ) {
            stmt.setString(1, textComment);
            try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comment = resultSetToComment(rs);
            }
        }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment getAllCommentsByUserId(long userId) throws SQLException {
        Comment comment = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement  stmt = connection.prepareStatement("SELECT * FROM Comments WHERE user_id = ?");
        ) {
            stmt.setLong(1, userId);
           try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment getAllCommentsByPostId(long postId) throws SQLException {
        Comment comment = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Comments WHERE post_id = ?");
        ) {
            stmt.setLong(1, postId);
        try(ResultSet rs = stmt.executeQuery()) {
            ;
            while (rs.next()) {
                comment = resultSetToComment(rs);
            }
        }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment insert(Comment entity) throws SQLException {
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Comments (commented_at, post_id, text_commented, user_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setString(3, entity.getTextComment());
            stmt.setLong(4, entity.getUserId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public Comment getById(Long id) throws SQLException {
        Comment comment = null;
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Comments WHERE comment_id = ?");
        ) {
            stmt.setLong(1, id);
            try (ResultSet  rs = stmt.executeQuery()) {
                while (rs.next()) {
                    comment = resultSetToComment(rs);
                }
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public Comment update(Comment entity) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("UPDATE Comments SET commented_at = ?, post_id = ?, text_comment = ?, user_id = ? " +
                    "WHERE comment_id = ?");
        ) {
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, entity.getPostId());
            stmt.setString(3, entity.getTextComment());
            stmt.setLong(4, entity.getUserId());
            stmt.setLong(5, entity.getId());
            stmt.executeQuery();
        }   catch (Exception e){
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        try (
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Comments WHERE comment_id = ?");
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }    catch (Exception e){
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
