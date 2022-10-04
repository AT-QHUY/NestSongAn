/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

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
            if (productDTO.getStatus()!=0)
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
            if (productDTO.getStatus()!=0)
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
            if (productDTO.getStatus()!=0)
                list.add(productDTO);
        }
        return list;
    }
    
    //get product by ID
    public List<ProductDTO> getProductById(int Id){
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO;
        List<ProductEntity> entityList = productDAO.getProductById(Id);
        if(entityList == null) return null;      
        for (ProductEntity product : entityList) {
            productDTO = ProductConverter.convertEntitytoDTO(product);
            if (productDTO.getStatus()!=0)
                list.add(productDTO);
        }
        return list;
    }
    
    public int updateProduct(ProductDTO product) {
        return productDAO.updateProduct(ProductConverter.convertDTOtoEntity(product));
        //Add roleID for user
    }
    
//    //search products by name
//        public List<ProductDTO> filter(ArayList<String> name){
//        List<ProductDTO> list = new ArrayList<>();
//        ProductDTO productDTO;
//        List<ProductEntity> entityList = productDAO.searchByName(name);
//        if(entityList == null) return null;      
//        for (ProductEntity product : entityList) {
//            productDTO = ProductConverter.convertEntitytoDTO(product);
//            if (productDTO.getStatus()!=0)
//                list.add(productDTO);
//        }
//        return list;
//    }
}
