/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author ADMIN
 */
public class ProductSQL {

    //add a new product *
    public static String addNewProduct = "insert dbo.Products (name, quantity, deal, description, basePrice, cateId, status)\n"
            + "Values (?,?,?,?,?,?,?)";

    //search product by name *
    public static String searchProductByName = "select * from dbo.Products\n"
            + "where lower(name) like lower(?) ";

    //get product by id  *
    public static String getProductById = "select * from dbo.Products\n"
            + "where id = ? ";

    //get product by CateId *
    public static String getProductByCateId = "select * from dbo.Products\n"
            +"where cateId = ?";


    //update Product 
    public static String updateProduct = "update dbo.Products\n"
            + "set name = ?, quantity = ?, deal = ?, description = ?, basePrice = ?, cateId = ?\n,"
            + "where id=?";
    
    //delete product (by set status = 0 in db, when it was 0 change to 1-undelete )
    public static String deleleProduct = "update dbo.Products\n"
            +"set status = ?"
            +"where id=?";

    //show all product *
    public static String showAll = "select * from dbo.Products";

//    public static String filter="select * from dbo.Products\n";
    
}
