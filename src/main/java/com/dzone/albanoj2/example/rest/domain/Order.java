package com.dzone.albanoj2.example.rest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order implements Identifiable {

	private Long id;
	private String description;
	private List<LineItem> lineItems = new ArrayList<>();
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
	}
	
	public void removeLineItem(LineItem lineItem) {
		lineItems.remove(lineItem);
	}
	
	public Long getTotalCostInCents() {
		return lineItems.stream().collect(Collectors.summingLong(item -> item.getCostInCents()));
	}
}
