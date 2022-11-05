/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.mapper;

import com.mygroup.nestsonganver2.entity.CommentEntity;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class CommentMapper implements RowMapper{
    public static CommentMapper instance;
    
    public static CommentMapper getInstance(){
        if(instance == null){
            instance = new CommentMapper();
        }
        return instance;
    }
    @Override
    public CommentEntity mapRow(ResultSet rs) {       
        try {
            CommentEntity comment = new CommentEntity();
            comment.setId(rs.getInt("id"));
            comment.setUserId(rs.getInt("userId"));
            comment.setProductId(rs.getInt("productId"));
            comment.setComment(rs.getString("comment"));
            comment.setRating(rs.getInt("rating"));
            return comment;
        } catch (SQLException ex) {
            Logger.getLogger(CommentMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
}
