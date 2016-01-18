/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprioritest.domain;

import java.util.List;

/**
 *
 * @author kiran
 */
public class Transaction {
    public String id;
	public List<String> item;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String id, List<String> item) {
		super();
		this.id = id;
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getItem() {
		return item;
	}

	public void setItem(List<String> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", item=" + item + "]";
	}
}
