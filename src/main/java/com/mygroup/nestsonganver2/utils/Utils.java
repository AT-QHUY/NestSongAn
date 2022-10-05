/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huy
 */
public class Utils {
     public static Connection makeConnection() {
        Connection conn = null;
        try {

            String dbURL = "jdbc:sqlserver://nestsongan.cqtchuhryqsc.ap-southeast-1.rds.amazonaws.com:1433;databaseName=NestSongAn";
            String user = "admin";
            String pass = "thisisadmin";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
//            System.out.println("Connect to DB successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String hashPassWordMd5(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
    }
     
}
