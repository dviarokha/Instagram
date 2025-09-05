package com.solvd.instagram.dao.mySQLIplm;

import com.solvd.instagram.dao.IPostTagDAO;
import com.solvd.instagram.models.PostTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostTagDAO extends MySQL implements IPostTagDAO<PostTag> {
    public static  final Logger logger = LogManager.getLogger(PostTagDAO.class);

    @Override
    public List<PostTag> getAllPostTags() throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PostTag> postTags = new ArrayList<>();
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Post_tags");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PostTag postTag = new PostTag();
                postTag.setPostId(rs.getLong(1));
                postTag.setPostId(rs.getLong(2));
                postTag.setTagId(rs.getLong(3));
                postTags.add(postTag);
            }
        }  catch (Exception e) {
           logger.error(e.getMessage());
        }  finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
        }
        return postTags;
    }

    @Override
    public PostTag getPostTagByTagId(long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PostTag postTag = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Post_tags WHERE tag_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                postTag = new PostTag();
                postTag.setPostId(rs.getLong("id_post"));
                postTag.setTagId(rs.getLong("id_tag"));
                postTag.setId(rs.getLong("id"));
            }
        }    catch (Exception e) {
            logger.error(e.getMessage());
        }   finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return postTag;
    }

    @Override
    public PostTag getPostTagByPostId(long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PostTag postTag = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM Post_tags WHERE post_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                postTag = new PostTag();
                postTag.setPostId(rs.getLong("id_post"));
                postTag.setTagId(rs.getLong("id_tag"));
                postTag.setId(rs.getLong("id"));
            }
        }   catch (Exception e) {
            logger.error(e.getMessage());
        }    finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return postTag;
    }

    @Override
    public PostTag insert(PostTag entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("INSERT INTO PostTags(post_id, tag_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, entity.getPostId());
            stmt.setLong(2, entity.getTagId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return entity;
    }

    @Override
    public PostTag getById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PostTag postTag = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("SELECT * FROM PostTags WHERE post_tag_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                postTag = new PostTag();
                postTag.setId(id);
                postTag.setPostId(rs.getLong(2));
                postTag.setTagId(rs.getLong(3));
            }
        }  catch (Exception e) {
            logger.error(e);
        }  finally {
            if (rs != null) {rs.close();}
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return postTag;
    }

    @Override
    public PostTag update(PostTag entity) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("UPDATE PostTags SET post_id = ?, tag_id = ? WHERE post_tag_id = ?");
            stmt.setLong(1, entity.getPostId());
            stmt.setLong(2, entity.getTagId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
        }  catch (Exception e) {
            logger.error(e);
        }  finally {
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
        return entity;
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instagram_model", "root", "");
            stmt = c.prepareStatement("DELETE FROM PostTags WHERE post_tag_id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }   catch (Exception e) {
            logger.error(e);
        }   finally {
            if (stmt != null) {stmt.close();}
            if (c != null) {c.close();}
        }
    }
}
