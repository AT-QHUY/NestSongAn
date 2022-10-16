/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import java.sql.Date;
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
    private List<BillDetailsDTO> CartLineItems;
    private static CartService instance;
    
    public static CartService gettCartSerivce() {
        if (instance == null)
            instance = new CartService();
        return instance;
    }
 
    private CartService() {
    }
    
    private void setCartBill(int userId) {
        if (this.bill == null) {
            List<BillDTO> cartBill  = billService.getBillByStatus(1);
            if (!cartBill.isEmpty()) 
                this.bill = cartBill.get(0);
            else {
                BillDTO newBill = new BillDTO();
                newBill.setCustomerId(userId);
                newBill.setStatus(1);
                newBill.setEmpId(1);
                newBill.setDate(Date.valueOf(LocalDate.now()));
                billService.insertNewBill(newBill);
                cartBill  = billService.getBillByStatus(1);
                if (!cartBill.isEmpty())
                this.bill = cartBill.get(0);
            }
        }
    }
    
    public boolean buy(int customerId) {
        setCartBill(customerId);
        this.bill.setDate(Date.valueOf(LocalDate.now()));
        if (billService.updateStatus(this.bill.getId(), 2) > 0) {
            this.bill = null;
            this.CartLineItems = null;
            return true;
        }
        return false;
    }   
    
    private void setCartLineItems(int billID) {
        if (this.CartLineItems == null) {
            List<BillDetailsDTO> newCartLineItem = billDetailsService.findByBillId(billID);
            if (newCartLineItem != null) this.CartLineItems = newCartLineItem;
            else this.CartLineItems = new ArrayList<>();
        }
    }
    private void resetCartLineItems (int billID) {
        this.CartLineItems = billDetailsService.findByBillId(billID);
    }

    
    public List<BillDetailsDTO> getCartLineItems(int customerId) {
        setCartBill(customerId);
        setCartLineItems(this.bill.getId());
        return this.CartLineItems;
    }
    
    public List<BillDetailsDTO> addToCart(BillDetailsDTO bd, int userId) {
        setCartBill(userId);
        setCartLineItems(this.bill.getId());
        for (BillDetailsDTO item : this.CartLineItems) {
            if (item.getProduct().getId() == bd.getProduct().getId()) {
                int newQuantity = item.getQuantity() + bd.getQuantity();
                item.setQuantity(newQuantity);
                float newPrice = newQuantity * item.getProduct().getBasePrice() - newQuantity * item.getProduct().getBasePrice() * item.getProduct().getDeal();
                item.setPrice(newPrice);
                billDetailsService.updateBillDetails(item.getId(), item);
                this.CartLineItems.add(bd);
//                resetCartLineItems(userId);
                return this.CartLineItems;
            }
        } 
        bd.setBillId(this.bill.getId());
        ProductDTO newProduct = productService.getProductById(bd.getProduct().getId());
        bd.setProduct(newProduct);
//        if (billDetailsService.insertNewBillDetails(bd) > 0) {
            billDetailsService.insertNewBillDetails(bd);
            resetCartLineItems(this.bill.getId());
            return this.CartLineItems;
//        }
//        return new ArrayList<>();
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
            if (billDetailsService.updateBillDetails(bdId, bd) > 0) {
                resetCartLineItems(billId);
                return 1;
            }   
        }
        return -1;
    }

    // -1: delete fail ; 0: not find billdetail; 1: delete successfully
    public int deleteBillDetail(int bdId, int userId) {
        setCartBill(userId);
        
        BillDetailsDTO deleteBD = billDetailsService.findById(bdId);
        if (deleteBD == null) return 0;
        if (deleteBD.getBillId() == bill.getId())
        {
            if (billDetailsService.deleteBillDetails(deleteBD) > 0){
                resetCartLineItems(this.bill.getId());
                return 1;
            }
            else return -1;
        }
        return 0;
    }

}
