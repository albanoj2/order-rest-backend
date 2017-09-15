package com.dzone.albanoj2.example.rest.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dzone.albanoj2.example.rest.domain.LineItem;

@Repository
@Scope("singleton")
public class LineItemRepository extends InMemoryRepository<LineItem> {

	@Override
	protected void updateIfExists(LineItem original, LineItem desired) {
		original.setName(desired.getName());
		original.setDescription(desired.getDescription());
		original.setCostInCents(desired.getCostInCents());
	}

}
