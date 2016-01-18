/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest.Dao;

import java.sql.SQLException;

/**
 *
 * @author bhanu
 */
public interface UserDao {
    public boolean login(String username, String password) throws SQLException;
}
