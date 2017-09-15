package com.dzone.albanoj2.example.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.dzone.albanoj2.example.rest.domain.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResource extends ResourceSupport {

	private final long id;
	private final String description;
	private final long costInCents;
	private final boolean isShipped;
	private final boolean isDelivered;
	private final boolean isCompleted;
	
	public OrderResource(Order order) {
		id = order.getId();
		description = order.getDescription();
		costInCents = order.getCostInCents();
		isShipped = order.isShipped();
		isDelivered = order.isDelivered();
		isCompleted = order.isCompleted();
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

	public boolean isShipped() {
		return isShipped;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public boolean isCompleted() {
		return isCompleted;
	}
}
