/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dd220
 */
public class CartService {

    private BillService billService = BillService.getInstance();
    private BillDetailsService billDetailsService = BillDetailsService.getInstance(); 
    private ProductService productService = ProductService.getInstance();
    private BillDTO bill;
    
    private static CartService instance;
    
    public static CartService gettCartSerivce() {
        if (instance == null)
            instance = new CartService();
        return instance;
    }
    
    private CartService() {}
    
    private void setCartBill(int customerId) {
        if (this.bill == null) {
            BillDTO cartBill  = billService.getBillByStatus(1).get(0);
            if (cartBill != null) 
                this.bill = cartBill;
            else {
                BillDTO newBill = new BillDTO();
                newBill.setCustomerId(customerId);
                newBill.setStatus(1);
                newBill.setEmpId(1);
                newBill.setDate(LocalDate.now());
                billService.insertNewBill(newBill);
                this.bill = billService.getBillByStatus(1).get(0);
            }
        }
    }
    
    private BillDTO getCartBill (int customerId) {
        setCartBill(customerId);
        return this.bill;
    }
    
    public List<BillDetailsDTO> getCartLineItems(int customerId) {
        setCartBill(customerId);
        List<BillDetailsDTO> bdList = billDetailsService.findByBillId(this.bill.getId());
        if (bdList == null) return new ArrayList<>();
        return bdList;
    }
    
    
    // -1: update fail ; 0: not find billdetail; 1: update successfully
    public int updateQuantity(int bdId, int quantity, int billId, int userId) {
        setCartBill(userId);
        BillDetailsDTO bd = billDetailsService.findById(bdId);
        if (billId == this.bill.getId()) {
            if (bd == null) return 0;
            bd.setQuantity(quantity);
            float price = quantity * bd.getProduct().getBasePrice() - quantity * bd.getProduct().getBasePrice() * bd.getProduct().getDeal() ;
            bd.setPrice(price);
            if (billDetailsService.updateBillDetails(bdId, bd) > 0) return 1;   
        }
        return -1;
    }

    // -1: delete fail ; 0: not find billdetail; 1: delete successfully
    public int deleteBillDetail(int bdId, int userId) {
        setCartBill(userId);
        BillDetailsDTO deleteBD = billDetailsService.findById(bdId);
        if (deleteBD == null) return 0;
        if (billDetailsService.deleteBillDetails(deleteBD) > 0) return 1;
        return 0;
    }

}
