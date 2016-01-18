/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest.dbConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kiran
 */
public class DBConnection {
    
    private Connection connection = null;
    
    private PreparedStatement preparedStatement;
    public DBConnection(String url,String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(url,user,pwd);
    }
    
    public DBConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recommenderDB","root","root");
    }
    
    public DBConnection(Connection connection) throws ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public PreparedStatement initStatement(String sql) throws SQLException
    {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }
  
    
    public int execute() throws SQLException
    {
        return    preparedStatement.executeUpdate();        
    }
    
    public int execute(String sql) throws SQLException
    {
        Statement stmt = connection.createStatement();
        int result = stmt.executeUpdate(sql);
        stmt.close();
        return result;
    }
    
    public ResultSet fetch(String sql) throws SQLException
    {
        ResultSet resultSet = null;
        
        Statement stmt = connection.createStatement();
        resultSet = stmt.executeQuery(sql);
        
        return resultSet;
    }
    
    public ResultSet fetch() throws SQLException
    {
        ResultSet resultSet = null;
        
        resultSet = preparedStatement.executeQuery();
        
        return resultSet;
    }
    
    public void close() throws SQLException
    {
        if(!connection.isClosed())
        {
            connection.close();
            connection = null;
        }
    }
    
}
