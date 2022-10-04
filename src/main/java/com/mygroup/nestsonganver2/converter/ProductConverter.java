/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.entity.ProductEntity;

/**
 *
 * @author ADMIN
 */
public class ProductConverter {
    // Convert Entitty to DTO
    
    public static ProductDTO convertEntitytoDTO(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setDeal(entity.getDeal());
        dto.setDescription(entity.getDescription());
        dto.setBasePrice(entity.getBasePrice());
        dto.setCateId(entity.getCateId());
        dto.setStatus(entity.getStatus());
        return dto;
    }   
    // -----------------------------------------------------------------------
    
    // Convert Entitty to DTO
    
    public static ProductEntity convertDTOtoEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setDeal(dto.getDeal());
        entity.setDescription(dto.getDescription());
        entity.setBasePrice(dto.getBasePrice());
        entity.setCateId(dto.getCateId());
        entity.setStatus(dto.getStatus());
        return entity;
    } 
    
    // -----------------------------------------------------------------------
}
