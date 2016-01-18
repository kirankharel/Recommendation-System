/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest;

import com.aprioritest.domain.Transaction;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class AprioriXMLPopulate {

    public static List<Transaction> populateItems() throws SAXException, IOException, ParserConfigurationException {
        
        List<Transaction> transactions = new ArrayList<Transaction>();
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document docRead = docBuilder.parse(new File("transactions.xml"));

        docRead.getDocumentElement().normalize();

//			System.out.println("Root element :" + docRead.getDocumentElement().getNodeName());
        NodeList nList = docRead.getElementsByTagName("transactionRecord");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

//                System.out.println("ID : " + eElement.getElementsByTagName("id").item(0).getTextContent());
//					System.out.println("Date : " + eElement.getElementsByTagName("date").item(0).getTextContent());
                
                Transaction t = new Transaction();
                List<String> items = new ArrayList<String>();
                t.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
                NodeList nodeList = eElement.getElementsByTagName("products").item(0).getChildNodes();
                for (int count = 1; count < nodeList.getLength(); count = count + 2) {
                    Node tempNode = nodeList.item(count);
                    items.add(tempNode.getTextContent());
//                    System.out.println("ProductId : " + tempNode.getTextContent());
                }
                t.setItem(items);
                
                transactions.add(t);
            }
            
        }
        return transactions;
    }
}
