package com.dzone.albanoj2.example.rest.domain;

public class LineItem {

	private long id;
	private String name;
	private String description;
	private long costInCents;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public long getCostInCents() {
		return costInCents;
	}

	public void setCostInCents(long costInCents) {
		this.costInCents = costInCents;
	}
}
