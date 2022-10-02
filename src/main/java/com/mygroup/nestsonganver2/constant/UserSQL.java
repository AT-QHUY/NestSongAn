/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.constant;

/**
 *
 * @author huy
 */
public class UserSQL {
    
     // Create user
    
    public static String insertNew = "INSERT  [dbo].[Users] (fullname, dateOfBirth, phoneNumber, address, username, password)\n"
                                   + "VALUES\n"
                                   + "(?, ?, ?, ?, ?, ?)";
    
    // ------------------------------------------------------------------------
    
    // Retrive user data
    
    public static String findAll = "select * from Users";
    public static String findById = "select * from Users where id = ?";   
    public static String login = "select * from Users where username = ? and password = ?";
    
    //Get User role name with id
    public static String getRoleById = "select roleName from Users join Roles \n"
                                     + "on Users.roleId = Roles.roleId \n"
                                     + "where Users.id = ?";
    
    //Get user role name with token
    public static String getRoleByToken = "select roleName from Users join Roles \n"
                                        + "on Users.roleId = Roles.roleId \n"
                                        + "where Users.token = ?";
    
    // ------------------------------------------------------------------------
    
    // Update user
    
     public static String updateStatus = "update Users set status = ? where id = ?";
     public static String updateUser = "update Users \n"
                                     + "set fullname = ? , dateOfBirth = ? , phoneNumber = ? , address = ? \n"
                                     + "where id = ? ";
     public static String updatePassword ="update Users \n"
                                     + "set password = ? \n"
                                     + "where id = ? and password = ? ";
    
    // ------------------------------------------------------------------------ 
    
}
