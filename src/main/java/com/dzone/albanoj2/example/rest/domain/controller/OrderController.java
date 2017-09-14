package com.dzone.albanoj2.example.rest.domain.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dzone.albanoj2.example.rest.domain.Order;
import com.dzone.albanoj2.example.rest.repository.OrderRepository;
import com.dzone.albanoj2.example.rest.resource.OrderResource;
import com.dzone.albanoj2.example.rest.resource.OrderResourceAssembler;

@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<OrderResource>> getOrders() {
		List<Order> orders = repository.findAll();
		return new ResponseEntity<>(assembler.toResourceCollection(orders), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderResource> createOrder(@RequestBody Order order) {
		Order createdOrder = repository.create(order);
		return new ResponseEntity<>(assembler.toResource(createdOrder), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderResource> findById(@PathVariable Long id) {
		Optional<Order> order = repository.findById(id);

		if (order.isPresent()) {
			return new ResponseEntity<>(assembler.toResource(order.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
