/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.ProductDAO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.entity.BillDetailsEntity;

/**
 *
 * @author Silver King
 */
public class BillDetailsConverter {
    private static ProductDAO productDAO = ProductDAO.getInstance();
    private static ProductConverter productConverter = ProductConverter.getInstance();
    
    public static BillDetailsDTO convertEntitytoDTO(BillDetailsEntity entity){
        BillDetailsDTO dto = new BillDetailsDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setProduct(productConverter.convertEntitytoDTO(productDAO.getProductById(entity.getId())));
        dto.setBillId(entity.getBillId());
        return dto;
    }
    
    public static BillDetailsEntity convertDTOtoEntity(BillDetailsDTO dto){
        BillDetailsEntity entity = new BillDetailsEntity();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setProductId(dto.getProduct().getId());
        entity.setBillId(dto.getBillId());
        return entity;
    }
}
