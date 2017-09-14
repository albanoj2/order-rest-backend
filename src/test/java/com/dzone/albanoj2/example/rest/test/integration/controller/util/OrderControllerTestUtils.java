package com.dzone.albanoj2.example.rest.test.integration.controller.util;

import static com.dzone.albanoj2.example.rest.test.integration.controller.util.ControllerTestUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.hateoas.EntityLinks;
import org.springframework.test.web.servlet.ResultMatcher;

import com.dzone.albanoj2.example.rest.domain.LineItem;
import com.dzone.albanoj2.example.rest.domain.Order;

public class OrderControllerTestUtils {

	public static ResultMatcher orderAtIndexIsCorrect(int index, Order expected) {
		return new CompositeResultMatcher()
			.addMatcher(jsonPath("$.[" + index + "].id").value(expected.getId()))
			.addMatcher(jsonPath("$.[" + index + "].description").value(expected.getDescription()))
			.addMatcher(jsonPath("$.[" + index + "].totalCostInCents").value(expected.getTotalCostInCents()));
	}
	
	public static ResultMatcher orderIsCorrect(Order expected) {
		return new CompositeResultMatcher()
			.addMatcher(jsonPath("$.id").value(expected.getId()))
			.addMatcher(jsonPath("$.description").value(expected.getDescription()))
			.addMatcher(jsonPath("$.totalCostInCents").value(expected.getTotalCostInCents()));
	}
	
	public static ResultMatcher updatedOrderIsCorrect(Long originalId, Order expected) {
		return new CompositeResultMatcher()
			.addMatcher(jsonPath("$.id").value(originalId))
			.addMatcher(jsonPath("$.description").value(expected.getDescription()))
			.addMatcher(jsonPath("$.totalCostInCents").value(expected.getTotalCostInCents()));
	}
	
	public static ResultMatcher orderLinksAtIndexAreCorrect(int index, Order expected, EntityLinks entityLinks) {
		return new CompositeResultMatcher()
			.addMatcher(selfLinkAtIndexIs(index, entityLinks.linkForSingleResource(expected).toString()))
			.addMatcher(lineItemLinkAtIndexIs(index, entityLinks.linkFor(LineItem.class, expected.getId()).toString()));
	}
	
	public static ResultMatcher orderLinksAreCorrect(Order expected, EntityLinks entityLinks) {
		return new CompositeResultMatcher()
			.addMatcher(selfLinkIs(entityLinks.linkForSingleResource(expected).toString()))
			.addMatcher(lineItemLinkIs(entityLinks.linkFor(LineItem.class, expected.getId()).toString()));
	}
}
