package com.dzone.albanoj2.example.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.dzone.albanoj2.example.rest.domain.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResource extends ResourceSupport {

	private final long id;
	private final String description;
	private final long costInCents;
	
	public OrderResource(Order order) {
		id = order.getId();
		description = order.getDescription();
		costInCents = order.getCostInCents();
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public long getCostInCents() {
		return costInCents;
	}
}
