/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.entity.BillEntity;

/**
 *
 * @author Silver King
 */
public class BillConverter {

    public static BillDTO convertEntitytoDTO(BillEntity entity) {
        BillDTO dto = new BillDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        dto.setCustomerId(entity.getCustomerId());
        dto.setEmpId(entity.getEmpId());
        return dto;
    }
    
    public static BillEntity convertDTOtoEntity(BillDTO dto){
        BillEntity entity = new BillEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
        entity.setCustomerId(dto.getCustomerId());
        entity.setEmpId(dto.getEmpId());
        return entity;
    }
}
