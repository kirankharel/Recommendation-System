/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioriTest.main;

import com.aprioritest.AprioriTest2;
import static com.aprioritest.AprioriTest2.createRecommendationList;
import com.aprioritest.AprioriXMLPopulate;
import com.aprioritest.Dao.Impl.UserDaoImpl;
import com.aprioritest.Dao.UserDao;
import com.aprioritest.domain.Transaction;
import com.aprioritest.domain.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author kiran
 */
public class MainProgram {
        public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
            
            User user = login();
            
            List<Transaction> transactions = populateList();
            AprioriTest2.createRecommendationList(transactions,user);

        
    }
        
        public static List<Transaction> populateList() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        
        List<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");        
        items.add("E");

        transactions.add(new Transaction("1", items));
        items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        transactions.add(new Transaction("2", items));

        items = new ArrayList<String>();
//		items.add("D");
        items.add("B");
        items.add("D");
        items.add("A");
        transactions.add(new Transaction("3", items));

        items = new ArrayList<String>();
        items.add("B");
        items.add("A");
        items.add("C");
        items.add("D");
        transactions.add(new Transaction("4", items));

        items = new ArrayList<String>();
        items.add("E");
        items.add("D");
        items.add("B");
        transactions.add(new Transaction("5", items));
        
        items = new ArrayList<String>();
        items.add("C");
        items.add("A");
        items.add("B");
        transactions.add(new Transaction("6", items));


        return transactions;
    }
        
        
        public static User login() throws ClassNotFoundException, SQLException{
            System.out.println("Welcome to RECOMMENDER SYSTEM");
            String username;
            String password;
            while(true){
            System.out.println("Enter your credentials");
            
            Scanner scan = new Scanner(System.in);
            System.out.println("username : ");
            username = scan.next();
            
            System.out.println("password : ");
            password = scan.next();
            
            UserDao uDAO = new UserDaoImpl();
                boolean loginCredentials = uDAO.login(username, password);
                if(loginCredentials){
                    break;
                }
            }
            return new User(username, password);
        }
}
