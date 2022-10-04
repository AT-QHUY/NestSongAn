/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.ProductSQL;
import com.mygroup.nestsonganver2.dao.IProductDAO;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import com.mygroup.nestsonganver2.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    private static ProductMapper productMapper = ProductMapper.getInstance();
    private static ProductDAO productDAO = null;

    public static ProductDAO getInstance() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    @Override
    public List<ProductEntity> showAll() {
        List<ProductEntity> productList = query(ProductSQL.showAll, productMapper);
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public List<ProductEntity> searchByName(String keyword) {
        List<ProductEntity> productList = query(ProductSQL.searchProductByName, productMapper, "%" + keyword + "%");
        return productList.isEmpty() ? null : productList;
    }

    @Override
    public int addNewProduct(ProductEntity product) {
        int id = insert(ProductSQL.addNewProduct, product.getName(), product.getQuantity(), product.getDeal(), product.getDescription(), product.getBasePrice(), product.getCateId(), product.getStatus());
        return id;
    }
    
     @Override
    public List<ProductEntity> getProductByCateId(int cateId) {
        List<ProductEntity> productList = query(ProductSQL.getProductByCateId, productMapper, cateId);
        return productList.isEmpty() ? null : productList;
    }
    
     @Override
    public List<ProductEntity> getProductById(int Id) {
        List<ProductEntity> productList = query(ProductSQL.getProductById, productMapper, Id);
        return productList.isEmpty() ? null : productList;
    }
    
    @Override
    public int updateProduct(ProductEntity product) {
        return update(ProductSQL.updateProduct, product.getName(), product.getQuantity(), product.getDeal(), product.getDescription(), product.getBasePrice(), product.getCateId());
    }
    
//    @Override
//    public List<ProductEntity> filter(ArrayList<String> name) {
//        String sql=ProductSQL.filter;
//        if (name!= null)
//        for (int i=0; i<name.size();i++)
//            sql+="where name = ?";
//        List<ProductEntity> productList = query(sql, productMapper, "%" + name + "%");
//        return productList.isEmpty() ? null : productList;
//    }

}
