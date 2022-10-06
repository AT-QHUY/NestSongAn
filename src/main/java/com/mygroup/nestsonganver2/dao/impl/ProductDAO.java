/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.ProductSQL;
import com.mygroup.nestsonganver2.dao.IProductDAO;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import com.mygroup.nestsonganver2.mapper.ProductMapper;
import java.util.List;
import com.mygroup.nestsonganver2.dto.Filter;
/**
 *
 * @author ADMIN
 */
public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    private static final ProductMapper productMapper = ProductMapper.getInstance();
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
    public ProductEntity getProductById(int Id) {
        List<ProductEntity> productList = query(ProductSQL.getProductById, productMapper, Id);
        return (productList.isEmpty()) ? null : productList.get(0);
    }

    @Override
    public int updateProduct(ProductEntity product) {
        return update(ProductSQL.updateProduct, product.getName(), product.getQuantity(), product.getDeal(), product.getDescription(), product.getBasePrice(), product.getCateId());
    }
    
    @Override
    public int setProductStatus(int isbn, int status) {   
        return update(ProductSQL.setProductStatus,status, isbn);
    }

    @Override
    public List<ProductEntity> filter(List<Filter> filter) {
        List<ProductEntity> productList = query(ProductSQL.showAll, productMapper);
        if (productList==null)
            return null;
        else
        while (!filter.isEmpty()) {
            productList = checkFilter(productList, filter.get(0).getName(), filter.get(0).getProperties());
            filter.remove(0);
        }
         return (productList ==null ||productList.isEmpty()) ? null : productList;
    }

    private List<ProductEntity> checkFilter(List<ProductEntity> productList, String object, String properties) {
        switch (object) {
            case "priceLow":
                for (int i = 0; i < productList.size();) {
                    if (productList.get(i).getBasePrice() < Float.parseFloat(properties)) {
                        productList.remove(i);
                    }else i++;
                }
                return productList;
            case "priceHigh":
                for (int i = 0; i < productList.size();) {
                    if (productList.get(i).getBasePrice() > Float.parseFloat(properties)) {
                        productList.remove(i);
                    }else i++;
                }
                return productList;
            case "deal":
                for (int i = 0; i != productList.size();) {
                    if (productList.get(i).getDeal() < Float.parseFloat(properties)) {
                        productList.remove(i);
                    }else i++;
                }

                return productList;

            case "name":
                for (int i = 0; i < productList.size();) {
                    if (!productList.get(i).getName().toLowerCase().contains(properties.toLowerCase())) {
                        productList.remove(i);
                    }
                    else i++;
                }
                return productList;
            case "cateId":
                for (int i = 0; i < productList.size();) {
                    if (productList.get(i).getCateId() != Integer.parseInt(properties)) {
                        productList.remove(i);
                    } else i++;
                }

                return productList;
        }
        return productList;
    }
    
     @Override
    public List<ProductEntity> getProductByPages(int page,int products) {
        int numberOfProducts= products* (page-1);
        List<ProductEntity> productList = query(ProductSQL.getProductByPages, productMapper, numberOfProducts, products);
        return (productList.isEmpty()) ? null : productList;
    }
    
}
