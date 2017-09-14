package com.dzone.albanoj2.example.rest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

	private long id;
	private List<LineItem> lineItems = new ArrayList<>();
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
	}
	
	public void removeLineItem(LineItem lineItem) {
		lineItems.remove(lineItem);
	}
	
	public long getTotalCostInCents() {
		return lineItems.stream().collect(Collectors.summingLong(item -> item.getCostInCents()));
	}
}
