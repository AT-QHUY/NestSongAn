/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.dao.impl.UserDAO;
import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.UserEntity;
import com.mygroup.nestsonganver2.converter.UserConverter;
import com.mygroup.nestsonganver2.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class UserService {

    private static final UserDAO userDAO = UserDAO.getInstance();

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    // Create User

    public String insertUser(UserDTO user) throws NoSuchAlgorithmException {
        if (checkLogin(user) != null) return null;
        user.setPassword(Utils.hashPassWordMd5(user.getPassword()));
        if(userDAO.createNewUser(UserConverter.convertDTOtoEntity(user)) == 0) return null;
        return UserConverter.ConvertDTOtoToken(user);
        
    }

    // ----------------------------------------------------------------------
    // Find User
    public String checkLogin(UserDTO user) throws NoSuchAlgorithmException {
        UserEntity userEntity = userDAO.findUser(user.getUsername(), Utils.hashPassWordMd5(user.getPassword()));
        if (userEntity != null) {
            user = UserConverter.convertEntitytoDTO(userEntity);
            //Add roleName for user
            return UserConverter.ConvertDTOtoToken(user);
        }else{
            return null;
        }
        
    }
    
    private UserDTO getUserById(int userId){
        UserDTO user = null;
        UserEntity userEntity = userDAO.findUser(userId);
         if (userEntity != null) {
            user = UserConverter.convertEntitytoDTO(userEntity);
            //Add roleName for user
        }
        return user;
    }

    public UserDTO getUserById(int userId, String token) {
        UserDTO userDTO = UserConverter.convertTokentoDTO(token);
        if (userDTO.getRoleName().equalsIgnoreCase("admin")
                || userDTO.getRoleName().equalsIgnoreCase("employee")
                || userDTO.getId() == userId) {           
            return getUserById(userId);
        }
       return null;
    }
    
    private List<UserDTO> findAllUsers(){
        List<UserDTO> list = new ArrayList<>();
        List<UserEntity> entityList = userDAO.findAll();
        for (UserEntity user : entityList) {              
            list.add(UserConverter.convertEntitytoDTO(user));
            //Add roleName for user
        }
        return list;
    }

     public List<UserDTO> findAllUsers(String token) {
        UserDTO userDTO = UserConverter.convertTokentoDTO(token);        
        if (userDTO.getRoleName().equalsIgnoreCase("admin")
                || userDTO.getRoleName().equalsIgnoreCase("employee")) {           
            return findAllUsers();
            }
        return null;
    }

    // ----------------------------------------------------------------------
    // Update User
     
    private int updateUser(UserDTO user){
        return userDAO.updateUser(UserConverter.convertDTOtoEntity(user));
    }
    
    public String updateUser(int id, UserDTO user) {
        int userID = getIdFromToken(user.getToken());      
        if(userID == id){
            user.setId(id);
            if(updateUser(user) != 0) return UserConverter.ConvertDTOtoToken(user);
            else return "";
        }
        return null;
        //Add roleName for user
    }
    
    public int updateUserStatus(int id, int status,String token) {
        if(getRoleNameFromToken(token).equalsIgnoreCase("admin")) return userDAO.updateUserStatus(id, status);
        else return 2;
    }
    
    public int updateUserPassword(int id, String oldPassword, String newPassword, String token) throws NoSuchAlgorithmException {
        if(getIdFromToken(token) == id) {
            return userDAO.updateUserPassword(id, Utils.hashPassWordMd5(oldPassword), Utils.hashPassWordMd5(newPassword));
        }
        return 2;
    }

    // ----------------------------------------------------------------------
    
    //Convert Token to id
    private int getIdFromToken(String token){
        UserDTO tmpDTO = null;
        tmpDTO = UserConverter.convertTokentoDTO(token);
        if(tmpDTO !=null ) return tmpDTO.getId();
        else return 0;
    }
    
    private String getRoleNameFromToken(String token){
        UserDTO tmpDTO = null;
        tmpDTO = UserConverter.convertTokentoDTO(token);
        if(tmpDTO !=null ) return tmpDTO.getRoleName();
        else return "";
    }
}
