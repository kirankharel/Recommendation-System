/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest;

import com.aprioritest.domain.Transaction;
import com.aprioritest.domain.User;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AprioriTest2 {

    /*The minimum support for creating frequent itemset*/
    static int minSupport = 3;

    /*Here,
        c1 is the candidate itemset for first iteration
        l1 is the frequent itemset for first iteration
    */
    static List<String> c1 = new ArrayList<String>();
    static List<String> l1 = new ArrayList<String>();

    /* lk represents kth frequent itemset*/
    static List<List<String>> lk = new ArrayList<List<String>>();

    public static void createRecommendationList(List<Transaction> transactions,User user) throws IOException {
        String filename = "ruleBase-" + user.getUsername()+ ".txt";
        FileWriter writer = new FileWriter(filename);
        List<List<String>> ck = AprioriTest2.generateFirstCandidateList(transactions);

        List<List<String>> ck1 = ck;
        
        /*Loop continues until we have an empty set of frequent itemset*/
        while (true) {

            lk = new ArrayList<>();
            for (List<String> itemSet : ck1) {

                for (int i = 0; i < ck1.size(); i++) {

                    String s = itemSet.toString();
                    String s1 = ck1.get(i).toString();

                    if (!s1.equals(s)) {
                        String s2 = "";
                        Set<String> temp = new HashSet<>();
                        for (String s3 : itemSet) {

                            temp.addAll(ck1.get(i));
                            temp.add(s3);
                            s2 = temp.toString();

                        }

                        List<String> temp2 = new ArrayList<String>();
                        temp2.addAll(temp);
                        if (!lk.contains(temp2)) {

                            if (temp2.size() < 4) {
                                lk.add(temp2);
                            }
                        }
                    }

                }

                System.out.println("lk inside loop " + lk);
            }

            System.out.println("Pruned lk : " + pruneItemSet(lk, transactions));
            lk = pruneItemSet(lk, transactions);

            /*Writing rule base of user in the file*/
            for (List<String> individualItemSet : lk) {

                if (individualItemSet.size() == 2) {
                    writer.write(individualItemSet.get(0) + ":" + individualItemSet.get(1) + "\r\n");
                }else{
                    writer.write(individualItemSet.get(0)+ "," +individualItemSet.get(1) + ":" + individualItemSet.get(2) + "\r\n");
                    writer.write(individualItemSet.get(0)+ "," +individualItemSet.get(2) + ":" + individualItemSet.get(1) + "\r\n");
                    writer.write(individualItemSet.get(1)+ "," +individualItemSet.get(2) + ":" + individualItemSet.get(0) + "\r\n");
                }
            }

            ck1 = lk;

            System.out.println(ck1);
            if (lk.size() == 0) {
                break;
            }
        }
        writer.close();
    }

    /*This method is used to prune those itemsets from candidate list which do not meet minimum support*/
    public static List<List<String>> pruneItemSet(List<List<String>> individualItemSet, List<Transaction> transactions) {
        List<List<String>> prunedLk = new ArrayList<List<String>>();
        Map<List<String>, Integer> lkCount = new HashMap<List<String>, Integer>();

        for (List<String> individualItem : individualItemSet) {
            lkCount.put(individualItem, 0);
        }

        System.out.println("lkCount : " + lkCount);
        for (Transaction t : transactions) {

            for (List<String> individualItem : individualItemSet) {
                int count = lkCount.get(individualItem);

                int occurenceCount = 0;
                for (String item : individualItem) {
                    if (t.getItem().contains(item)) {
                        occurenceCount++;
                    }
                }
                if (occurenceCount == individualItem.size()) {
                    lkCount.put(individualItem, ++count);
                }
            }
        }

        for (List<String> individualItem : individualItemSet) {
            int count = lkCount.get(individualItem);
            if (count < minSupport) {
                lkCount.remove(individualItem);
                System.out.println(individualItem + " has been pruned");
            } else {
                System.out.println(individualItem + " count : " + lkCount.get(individualItem));
                prunedLk.add(individualItem);
            }

        }

        return prunedLk;
    }

    /*The function generates the first candidate list*/
    public static List<List<String>> generateFirstCandidateList(List<Transaction> transactions) {
        HashMap<String, Integer> lCount = new HashMap<String, Integer>();
        for (Transaction t : transactions) {
            List<Integer> arraysize = new ArrayList<Integer>();
            arraysize.add(0);

            for (String item : t.getItem()) {
                if (!c1.contains(item)) {
                    c1.add(item);
                    lCount.put(item, 0);
                }
            }

            System.out.println(t.toString());
        }
        System.out.println(c1);

        for (Transaction t : transactions) {

            for (String item : t.getItem()) {
                int count = lCount.get(item);
                if (c1.contains(item)) {
                    lCount.put(item, ++count);
                }
            }
        }

        for (String item : c1) {
            int count = lCount.get(item);
            if (count < minSupport) {
                lCount.remove(item);
                System.out.println(item + " has been pruned");
            } else {
                l1.add(item);
            }

        }

        List<List<String>> newItemSet = new ArrayList<List<String>>();
        for (String item : l1) {
            System.out.println("Count for " + item + " is " + lCount.get(item));
            List<String> setItem = new ArrayList<String>();
            setItem.add(item);
            newItemSet.add(setItem);
        }

        return newItemSet;

    }

}
