    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.UserSQL;
import com.mygroup.nestsonganver2.dao.IUserDAO;
import com.mygroup.nestsonganver2.entity.UserEntity;
import com.mygroup.nestsonganver2.mapper.RowMapper;
import com.mygroup.nestsonganver2.mapper.UserMapper;
import java.util.List;

/**
 *
 * @author huy
 */
public class UserDAO extends AbstractDAO<UserEntity> implements IUserDAO {

    private static UserDAO userDAO = null;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    // Create User
    @Override
    public int createNewUser(UserEntity user) {

        int id = insert(UserSQL.insertNew, user.getFullname(), user.getDateOfBirth(), user.getPhoneNumber(), user.getAddress(), user.getUsername(), user.getPassword());
        return id;
    }

    // ----------------------------------------------------------------------
    // Find User
    @Override
    public UserEntity findUser(String username, String password) {
        List<UserEntity> userList = query(UserSQL.login, new UserMapper(), username, password);
        return (userList.isEmpty() || userList.get(0).getStatus() == 0) ? null : userList.get(0);
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> userList = query(UserSQL.findAll, new UserMapper());
        return userList.isEmpty() ? null : userList;
    }

    @Override
    public UserEntity findUser(int id) {
        List<UserEntity> userList = query(UserSQL.findById, new UserMapper(), id);
        return (userList.isEmpty() || userList.get(0).getStatus() == 0) ? null : userList.get(0);
    }

    // ----------------------------------------------------------------------
    // Update User
    @Override
    public int updateUser(UserEntity user) {
        return update(UserSQL.updateUser, user.getFullname(), user.getDateOfBirth(), user.getPhoneNumber(), user.getAddress(), user.getUsername(), user.getId());
    }

    @Override
    public int updateUserStatus(int id, int status) {
        return update(UserSQL.updateStatus, id, status);
    }

    @Override
    public int updateUserPassword(int id, String password) {
        return update(UserSQL.updatePassword, password, id);
    }

    // ----------------------------------------------------------------------
}
