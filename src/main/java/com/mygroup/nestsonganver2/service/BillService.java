/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.BillConverter;
import com.mygroup.nestsonganver2.dao.impl.BillDAO;
import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.entity.BillEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillService {

    private static final BillDAO billDAO = BillDAO.getInstance();
    
    private static BillService billService = null;
    
    public static BillService getInstance(){
        if (billService==null){
            billService = new BillService();
        }
        return billService;
    }
    
    //--------------------------------------------------------------------------
    //create new bill
    public int insertNewBill(BillDTO bill){
        return billDAO.createNewBill(BillConverter.convertDTOtoEntity(bill));
    }
    
    //--------------------------------------------------------------------------
    //find bill
    public List<BillDTO> getAllBill(){
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.finndAll();
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }
    
    public BillDTO getBillById(int id) {
        BillDTO bill = null;
        BillEntity billEntity = billDAO.findBillById(id);
        if (billEntity != null  ) {
            bill = BillConverter.convertEntitytoDTO(billEntity);
        }
        return bill;
    }
    
    public List<BillDTO> getBillByStatus(int status){
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.findBillByStatus(status);
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }
    
      public List<BillDTO> getBillByCustomerId(int customerId){
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.findBillByCustomerId(customerId);
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }  public List<BillDTO> getBillByEmpId(int empId){
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.findBillByEmpId(empId);
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }
    
    //--------------------------------------------------------------------------
    //update bill
    public int updateStatus(int id, int status){
        return billDAO.updateStatus(id, status);
    }
    
    public int updateBill(int id, BillDTO bill){
        bill.setId(id);
        return billDAO.updateBill(BillConverter.convertDTOtoEntity(bill));
    }
    
    public List<BillDTO> getBillByEmpIdAndStatus(int empId,int status) {
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.findByEmpIdAndStatus(empId,status);
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }

    public List<BillDTO> getBillByCUstomerIdAndStatus(int empId, int status) {
        List<BillDTO> result = new ArrayList<>();
        BillDTO dto = null;
        List<BillEntity> listEntity = billDAO.findByCustomerIdAndStatus(empId, status);
        for (BillEntity billEntity : listEntity) {
            dto = BillConverter.convertEntitytoDTO(billEntity);
            result.add(dto);
        }
        return result;
    }
}
