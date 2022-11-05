/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.CommentDTO;
import com.mygroup.nestsonganver2.entity.CommentEntity;

/**
 *
 * @author ADMIN
 */
public class CommentConverter {
    public static CommentDTO convertEntitytoDTO(CommentEntity entity){
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setProductId(entity.getProductId());
        dto.setComment(entity.getComment());
        dto.setRating(entity.getRating());
        return dto;
    }   
    // -----------------------------------------------------------------------
    
    // Convert Entitty to DTO
    
    public static CommentEntity convertDTOtoEntity(CommentDTO dto){
        CommentEntity entity = new CommentEntity();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setProductId(dto.getProductId());
        entity.setComment(dto.getComment());
        entity.setRating(dto.getRating());
        return entity;
    } 
}
