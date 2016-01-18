/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest.Dao.Impl;

import com.aprioritest.Dao.UserDao;
import com.aprioritest.dbConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bhanu
 */
public class UserDaoImpl implements UserDao{
    DBConnection connection;

    public UserDaoImpl() throws ClassNotFoundException, SQLException {
        this.connection = new DBConnection();
    }
    
    @Override
    public boolean login(String username, String password) throws SQLException{
               String sql = "Select password from user WHERE username LIKE ?";
                PreparedStatement stmt = connection.initStatement(sql);
                stmt.setString(1, username); 
                
                ResultSet rs = connection.fetch();
                
                while(rs.next()){
                    String pw = rs.getString("password");
                    
                    if(pw.equals(password)){
                        return true;
                    }
                }
                
                return false;
    }
    
}
