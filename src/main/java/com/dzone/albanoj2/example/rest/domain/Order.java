package com.dzone.albanoj2.example.rest.domain;

public class Order implements Identifiable {

	private Long id;
	private String description;
	private long costInCents;
	private boolean isComplete;
	
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
	
	public void setCostInCents(long cost) {
		costInCents = cost;
	}
	
	public long getCostInCents() {
		return costInCents;
	}
	
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public void markComplete() {
		setComplete(true);
	}
	
	public void markIncomplete() {
		setComplete(false);
	}
	
	public boolean isComplete() {
		return isComplete;
	}
}
