/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dto;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDTO {

    private int id;
    private Date date;
    private int status;
    private int customerId;
    private int empId;
    private List<BillDetailsDTO> listBillDetails;

    private static BillDTO billDTO = null;

    public static BillDTO getInstance() {
        if (billDTO == null) {
            billDTO = new BillDTO();
        }
        return billDTO;
    }

    public BillDTO() {
    }

    public BillDTO(int id, Date date, int status, int customerId, int empId, List<BillDetailsDTO> listBillDetails) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.empId = empId;
        this.listBillDetails = listBillDetails;
    }

    public List<BillDetailsDTO> getListBillDetails() {
        return listBillDetails;
    }

    public void setListBillDetails(List<BillDetailsDTO> listBillDetails) {
        this.listBillDetails = listBillDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

}
