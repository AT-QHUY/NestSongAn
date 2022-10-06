/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.dto.Filter;
import com.mygroup.nestsonganver2.converter.ProductConverter;
import com.mygroup.nestsonganver2.dao.impl.ProductDAO;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.entity.ProductEntity;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class ProductService {
    private static final ProductDAO productDAO = ProductDAO.getInstance();

    private static ProductService productService;

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }
    
    //show all products
    public List<ProductDTO> showAllProducts() {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.showAll();
        if(entityList == null) return null; 
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            list.add(productDTO);
        }
        return list;
    }
    
    //search products by name
        public List<ProductDTO> searchByName(String keyword){
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.searchByName(keyword);
        if(entityList == null) return null;      
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            list.add(productDTO);
        }
        return list;
    }
        
    //add a new product
    public int addNewProduct(ProductDTO product) throws NoSuchAlgorithmException {
         return productDAO.addNewProduct(ProductConverter.convertDTOtoEntity(product));
    }
   
    //get products by CateId
    public List<ProductDTO> getProductByCateId(int cateId){
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.getProductByCateId(cateId);
        if(entityList == null) return null;      
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            list.add(productDTO);
        }
        return list;
    }
    
    //get product by ID
    public ProductDTO getProductById(int Id){
        ProductDTO productDTO;
        ProductEntity entity = productDAO.getProductById(Id);
        if(entity == null) return null;      
        productDTO = ProductConverter.convertEntitytoDTO(entity);
        return productDTO;
   
    }
    
    public int updateProduct(ProductDTO product) {
        return productDAO.updateProduct(ProductConverter.convertDTOtoEntity(product));
        //Add roleID for user
    }
    
    public int setProductStatus(int isbn, int status) {
        return productDAO.setProductStatus( isbn, status);
    }
    //use filter
        public List<ProductDTO> filter(List<Filter> filter){
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.filter(filter);
        if(entityList == null) return null;      
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            list.add(productDTO);
        }
        return list;
    }
        
    //get products by gages
       public List<ProductDTO> getProductByPages(int page,int products){
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.getProductByPages(page, products);
        if(entityList == null) return null;      
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            list.add(productDTO);
        }
        return list;
    } 
}


