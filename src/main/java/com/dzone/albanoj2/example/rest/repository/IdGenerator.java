package com.dzone.albanoj2.example.rest.repository;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

	private long nextId = 1;
	
	public long getNextId() {
		return nextId++;
	}
}
