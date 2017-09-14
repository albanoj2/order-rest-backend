package com.dzone.albanoj2.example.rest.domain;

import org.springframework.hateoas.Identifiable;

public class LineItem implements Identifiable<Long> {

	private Long id;
	private String name;
	private String description;
	private Long costInCents;
	
	public LineItem() {}
	
	public LineItem(String name, String description, Long costInCents) {
		this.name = name;
		this.description = description;
		this.costInCents = costInCents;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	public Long getCostInCents() {
		return costInCents;
	}

	public void setCostInCents(Long costInCents) {
		this.costInCents = costInCents;
	}
}
