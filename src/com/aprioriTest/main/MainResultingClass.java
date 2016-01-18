/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioriTest.main;

import static com.aprioriTest.main.MainProgram.login;
import com.aprioritest.domain.User;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author kiran
 */
public class MainResultingClass {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
         User user = MainProgram.login();
         String fileName = "ruleBase-"+user.getUsername()+".txt";
         FileReader freader = new FileReader(fileName);
         
         BufferedReader reader = new BufferedReader(freader);
         String content = "";
         Set<String> recommendedItems = new HashSet<String>();
         while((content = reader.readLine())!= null){
             String[] recommendation = content.split(":");
             recommendedItems.add(recommendation[1]);
         }
         
         System.out.println("Recommended Items : " + recommendedItems);
    }
    
}
