/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.BillDetailsConverter;
import com.mygroup.nestsonganver2.dao.impl.BillDetailsDAO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.entity.BillDetailsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDetailsService {

    private static final BillDetailsDAO billDetailsDAO = BillDetailsDAO.getInstance();

    private static BillDetailsService billDetailsService = null;

    public static BillDetailsService getInstance() {
        if (billDetailsService == null) {
            billDetailsService = new BillDetailsService();
        }
        return billDetailsService;
    }

    //--------------------------------------------------------------------------
    //insert new bill details
    public int insertNewBillDetails(BillDetailsDTO billDetails) {
        return billDetailsDAO.insertNewBillDetails(BillDetailsConverter.convertDTOtoEntity(billDetails));
    }

    //--------------------------------------------------------------------------
    //find bill details by...
    public List<BillDetailsDTO> findAll() {
        List<BillDetailsDTO> result = new ArrayList<>();
        List<BillDetailsEntity> listEntity = billDetailsDAO.findAll();
        if (!listEntity.isEmpty()) {
            result = BillDetailsConverter.convertListEntitytoDTO(listEntity);
        }
        return result;
    }

    public List<BillDetailsDTO> findByBillId(int id) {
        List<BillDetailsDTO> result = new ArrayList<>();
        List<BillDetailsEntity> listEntity = billDetailsDAO.findByBillId(id);
        if (!listEntity.isEmpty()) {
            result = BillDetailsConverter.convertListEntitytoDTO(listEntity);
        }
        return result;
    }

    public BillDetailsDTO findById(int id) {
        BillDetailsDTO result = null;
        BillDetailsEntity entity = billDetailsDAO.findById(id);
        if (entity != null) {
            result = BillDetailsConverter.convertEntitytoDTO(entity);
        }
        return result;
    }

    //--------------------------------------------------------------------------
    //update
    public int updateBillDetails(int id, BillDetailsDTO billDetails) {
        billDetails.setId(id);
        return billDetailsDAO.updateBillDetails(BillDetailsConverter.convertDTOtoEntity(billDetails));
    }

    //--------------------------------------------------------------------------
    //delete 
    public int deleteBillDetails(BillDetailsDTO billDetails) {
        return billDetailsDAO.deleteBillDetails(BillDetailsConverter.convertDTOtoEntity(billDetails));
    }
}
