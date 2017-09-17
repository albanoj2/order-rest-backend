package com.dzone.albanoj2.example.rest.resource;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.dzone.albanoj2.example.rest.domain.Order;

@Component
public class OrderResourceAssembler extends ResourceAssembler<Order, OrderResource> {

	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";

	@Override
	public OrderResource toResource(Order order) {
		
		OrderResource resource = new OrderResource(order);
		
		final Link selfLink = entityLinks.linkToSingleResource(Order.class, order.getId());
		
		resource.add(selfLink.withSelfRel());
		resource.add(selfLink.withRel(UPDATE_REL));
		resource.add(selfLink.withRel(DELETE_REL));
		
		return resource;
	}
}
