/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.BillDetailsDAO;
import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import com.mygroup.nestsonganver2.entity.BillEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillConverter {

    private static BillDetailsDAO billDetailsDAO = BillDetailsDAO.getInstance();

    public static BillDTO convertEntitytoDTO(BillEntity entity) {
        BillDTO dto = new BillDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        dto.setCustomerId(entity.getCustomerId());
        dto.setEmpId(entity.getEmpId());
        dto.setListBillDetails(BillDetailsConverter.convertListEntitytoDTO(billDetailsDAO.findByBillId(entity.getId())));
        return dto;
    }

    public static BillEntity convertDTOtoEntity(BillDTO dto) {
        BillEntity entity = new BillEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
        entity.setCustomerId(dto.getCustomerId());
        entity.setEmpId(dto.getEmpId());
        return entity;
    }

    public static List<BillDTO> convertListEntitytoDTO(List<BillEntity> list) {
        List<BillDTO> result = new ArrayList<>();
        for (BillEntity billEntity : list) {
            BillDTO dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }

    public static List<BillEntity> convertListDTOtoEntity(List<BillDTO> list) {
        List<BillEntity> result = new ArrayList<>();
        for (BillDTO billDTO : list) {
            result.add(BillConverter.convertDTOtoEntity(billDTO));
        }
        return result;
    }
}
