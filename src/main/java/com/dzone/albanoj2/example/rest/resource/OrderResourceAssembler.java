package com.dzone.albanoj2.example.rest.resource;

import org.springframework.stereotype.Component;

import com.dzone.albanoj2.example.rest.domain.LineItem;
import com.dzone.albanoj2.example.rest.domain.Order;

@Component
public class OrderResourceAssembler extends ResourceAssembler<Order, OrderResource> {

	@Override
	public OrderResource toResource(Order order) {
		
		OrderResource resource = new OrderResource(order);
		
		resource.add(entityLinks.linkToSingleResource(Order.class, order.getId()).withSelfRel());
		resource.add(entityLinks.linkFor(LineItem.class, order.getId()).withRel("lineItems"));
		
		return resource;
	}
}
