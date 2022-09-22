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

    public int insertUser(UserDTO user) throws NoSuchAlgorithmException {
        if (checkLogin(user) == null) {
            return 0;
        }
        user.setPassword(Utils.hashPassWordMd5(user.getPassword()));
        return userDAO.createNewUser(UserConverter.convertDTOtoEntity(user));
    }

    // ----------------------------------------------------------------------
    // Find User
    public UserDTO checkLogin(UserDTO user) throws NoSuchAlgorithmException {
        UserEntity userEntity = userDAO.findUser(user.getUsername(), Utils.hashPassWordMd5(user.getPassword()));
        if (userEntity != null) {
            user = UserConverter.convertEntitytoDTO(userEntity);
            //Add roleName for user
            return user;
        }else{
            return null;
        }
        
    }

    public UserDTO getUserById(int userId) {
        UserDTO user = null;
        UserEntity userEntity = userDAO.findUser(userId);
        if (userEntity != null) {
            user = UserConverter.convertEntitytoDTO(userEntity);
            //Add roleName for user
        }
        return user;
    }

    public List<UserDTO> findAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        UserDTO userDTO;
        List<UserEntity> entityList = userDAO.findAll();
        for (UserEntity user : entityList) {
            userDTO = UserConverter.convertEntitytoDTO(user);
            //Add roleName for user
            list.add(userDTO);
        }
        return list;
    }

    // ----------------------------------------------------------------------
    // Update User
    
    public int updateUser(UserDTO user) {
        return userDAO.updateUser(UserConverter.convertDTOtoEntity(user));
        //Add roleID for user
    }
    
    public int updateUserStatus(int id, int status) {
        return userDAO.updateUserStatus(id, status);
    }
    
    public int updateUserPassword(int id, String password) throws NoSuchAlgorithmException {
        return userDAO.updateUserPassword(id, Utils.hashPassWordMd5(password));
    }

    // ----------------------------------------------------------------------
}
