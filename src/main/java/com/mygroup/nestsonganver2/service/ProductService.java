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
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductService {

    private static final ProductDAO productDAO = ProductDAO.getInstance();

    private static final ProductConverter productConverter = ProductConverter.getInstance();

    private static ProductService productService;

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    //show all products
    public List<ProductDTO> showAllProducts() {
        List<ProductEntity> entityList = productDAO.showAll();
        if (entityList == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entityList);
    }

    //search products by name
    public List<ProductDTO> searchByName(String keyword) {
        List<ProductEntity> entityList = productDAO.searchByName(keyword);
        if (entityList == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entityList);
    }

    //add a new product
    public int addNewProduct(ProductDTO product) throws NoSuchAlgorithmException {
        return productDAO.addNewProduct(ProductConverter.convertDTOtoEntity(product));
    }

    //get products by CateId
    public List<ProductDTO> getProductByCateId(int cateId) {
        List<ProductEntity> entityList = productDAO.getProductByCateId(cateId);
        if (entityList == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entityList);
    }

    //get product by ID
    public ProductDTO getProductById(int Id) {
        ProductEntity entity = productDAO.getProductById(Id);
        if (entity == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entity);

    }

    public int updateProduct(ProductDTO product) {
        return productDAO.updateProduct(ProductConverter.convertDTOtoEntity(checkBeforeUpdate(product)));
        //Add roleID for user
    }
    
    private ProductDTO checkBeforeUpdate(ProductDTO product){
        ProductDTO oldProductDTO= getProductById(product.getId());
        if (product.getName()==null)
            product.setName(oldProductDTO.getName());
        if (product.getQuantity()==0)
            product.setQuantity(oldProductDTO.getQuantity());
        if (product.getDeal()==0)
            product.setDeal(oldProductDTO.getDeal());
        if (product.getDescription()==null)
            product.setDescription(oldProductDTO.getDescription());
        if (product.getBasePrice()==0)
            product.setBasePrice(oldProductDTO.getBasePrice());
        if (product.getCateId()==0)
            product.setCateId(oldProductDTO.getCateId());
        return product;
    }

    public int setProductStatus(int isbn, int status) {
        return productDAO.setProductStatus(isbn, status);
    }

    //use filter
    public List<ProductDTO> filter(Filter filter) {
        List<ProductEntity> entityList;
        if (filter.getName()==null && filter.getLowPrice()==0 && filter.getHighPrice()==0 && filter.getDeal()==0 &&filter.getDeal()==0)
            entityList = productDAO.showAll(); 
        else 
            entityList = productDAO.filter(filter);
        if (entityList == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entityList);
    }

    //get products by gages
    public List<ProductDTO> getProductByPages(int page, int limit) {
        List<ProductEntity> entityList = productDAO.getProductByPages(page, limit);
        if (entityList == null) {
            return null;
        }
        return productConverter.convertEntitytoDTO(entityList);
    }

}
