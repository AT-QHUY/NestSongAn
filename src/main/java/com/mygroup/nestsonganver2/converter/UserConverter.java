/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.UserEntity;

/**
 *
 * @author huy
 */
public class UserConverter {
    
    public static UserDTO convertEntitytoDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setFullname(entity.getFullname());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setPassword(entity.getPassword());
        dto.setRoleId(entity.getRoleId());
        dto.setToken(entity.getToken());
        return dto;
    } 
    
    public static UserEntity convertDTOtoEntity(UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setFullname(dto.getFullname());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setPassword(dto.getPassword());
        entity.setRoleId(dto.getRoleId());
        entity.setToken(dto.getToken());
        return entity;
    } 
    
}
