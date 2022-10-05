/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.BillSQL;
import com.mygroup.nestsonganver2.dao.IBillDAO;
import com.mygroup.nestsonganver2.entity.BillEntity;
import com.mygroup.nestsonganver2.mapper.BillMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Silver King
 */
public class BillDAO extends AbstractDAO<BillEntity> implements IBillDAO {

    private static BillDAO billDAO = null;

    public static BillDAO getInstance() {
        if (billDAO == null) {
            billDAO = new BillDAO();
        }
        return billDAO;
    }

    //create new bill
    @Override
    public int createNewBill(BillEntity bill) {
        int id = insert(BillSQL.insertNew, bill.getDate(), bill.getStatus(), bill.getCustomerId(), bill.getEmpId());
        return id;
    }

    //--------------------------------------------------------------------------
    //find bill by.....
    @Override
    public List<BillEntity> finndAll() {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findAll, new BillMapper());
        return list;
    }

    @Override
    public BillEntity findBillById(int id) {
        BillEntity result = new BillEntity();
        List<BillEntity> list = query(BillSQL.findById, new BillMapper(), id);
        if (!list.isEmpty() || list.get(0) != null) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public List<BillEntity> findBillByStatus(int status) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByStatus, new BillMapper(), status);
        return list;
    }

    @Override
    public List<BillEntity> findBillByCustomerId(int customerId) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByCustomerId, new BillMapper(), customerId);
        return list;
    }

    @Override
    public List<BillEntity> findBillByEmpId(int empId) {
        List<BillEntity> list = new ArrayList<>();
        list = query(BillSQL.findByEmpId, new BillMapper(), empId);
        return list;
    }

    //--------------------------------------------------------------------------
    // update bill
    @Override
    public int updateBill(BillEntity bill) {
        int result = update(BillSQL.updateBill, bill.getDate(), bill.getStatus(), bill.getCustomerId(), bill.getEmpId(), bill.getId());
        return result;
    }

    //delete (update status)
    @Override
    public int updateStatus(int id, int status) {
        int result = update(BillSQL.updateStatus, status, id);
        return result;
    }
}
