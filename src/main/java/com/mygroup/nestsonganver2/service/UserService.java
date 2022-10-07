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
import com.mygroup.nestsonganver2.dao.impl.RoleDAO;
import com.mygroup.nestsonganver2.entity.RoleEntity;
import com.mygroup.nestsonganver2.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class UserService {
    
    private RoleDAO roleDAO = RoleDAO.getRoleDAO();
    
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
    public String checkLogin(UserDTO user) throws NoSuchAlgorithmException {
        UserEntity userEntity = userDAO.findUser(user.getUsername(), Utils.hashPassWordMd5(user.getPassword()));
        if (userEntity != null) {
            RoleEntity role = roleDAO.getRoleById(userEntity.getRoleId());
            user = UserConverter.convertEntitytoDTO(userEntity, role);
            return UserConverter.ConvertDTOtoToken(user);
        }
        return null;
    }
    
    public UserDTO getUserById(int userId, int tokenId, String tokenRole) {
        if (tokenRole.equalsIgnoreCase("admin")
            || tokenRole.equalsIgnoreCase("employee")
            || tokenId == userId)
            return getUserById(userId);
        return null;
    }
    

    private UserDTO getUserById(int userId) {
        UserEntity userEntity = userDAO.findUser(userId);
        if (userEntity.getId() != 0) {
            RoleEntity role = roleDAO.getRoleById(userEntity.getRoleId());
            return UserConverter.convertEntitytoDTO(userEntity, role);
        }
        return new UserDTO();
    }
    
    public List<UserDTO> findAllUsers(String tokenRole){
        if(tokenRole.equalsIgnoreCase("admin")) return findAllUsers();
        return null;
    }

    private List<UserDTO> findAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        UserDTO userDTO;
        List<UserEntity> entityList = userDAO.findAll();
        List<RoleEntity> roleList = roleDAO.getAllRole();
        for (UserEntity user : entityList) {
            RoleEntity role= new RoleEntity();
            if (!roleList.isEmpty()) 
                for (RoleEntity roleCheck : roleList) 
                    if (roleCheck.getId() == user.getRoleId()) {
                        role = roleCheck;
                        break;
                    }
            userDTO = UserConverter.convertEntitytoDTO(user, role);
            list.add(userDTO);
        }
        return list;
    }

    // ----------------------------------------------------------------------
    // Update User
    
    public int updateUser(UserDTO user) {
        return userDAO.updateUser(UserConverter.convertDTOtoEntity(user));
    }
    
    public int updateUserStatus(int id, int status) {
        return userDAO.updateUserStatus(status, id);
    }
    
    public int updateUserPassword(int id, String password) throws NoSuchAlgorithmException {
        return userDAO.updateUserPassword(id, Utils.hashPassWordMd5(password));
    }

    // ----------------------------------------------------------------------
}
