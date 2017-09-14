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
	
	public boolean delete(Long id) {
		return elements.removeIf(element -> element.getId().equals(id));
	}
	
	public boolean update(Long id, Order updatedElement) {
		
		Optional<Order> element = findById(id);
		
		element.ifPresent(e -> {
			e.setDescription(updatedElement.getDescription());
			e.setLineItems(updatedElement.getLineItems());
		});
		
		return element.isPresent();
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
