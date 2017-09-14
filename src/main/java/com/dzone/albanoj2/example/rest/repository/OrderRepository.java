package com.dzone.albanoj2.example.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dzone.albanoj2.example.rest.domain.Order;

@Repository
@Scope("singleton")
public class OrderRepository {

	private static long NEXT_ID = 1;
	private List<Order> elements = new ArrayList<>();
	
	public Order create(Order element) {
		elements.add(element);
		element.setId(getNextId());
		return element;
	}
	
	private static long getNextId() {
		return NEXT_ID++;
	}
	
	public void delete(Order element) {
		elements.remove(element);
	}
	
	public void update(Long id, Order element) {
		findById(id).ifPresent(e -> {
			e.setDescription(element.getDescription());
			e.setLineItems(element.getLineItems());
		});
	}
	
	public List<Order> findAll() {
		return elements;
	}
	
	public Optional<Order> findById(Long id) {
		return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
	}
	
	public int getCount() {
		return elements.size();
	}
	
	public void clear() {
		elements.clear();
	}
}
