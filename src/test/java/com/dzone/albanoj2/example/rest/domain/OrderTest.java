package com.dzone.albanoj2.example.rest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	
	@Before
	public void setUp() {
		order = new Order();
	}

	@Test
	public void testOrderWithNoLineItemsEnsureCostIsZero() {
		assertNoLineItems();
		assertTotalCostInCentsIs(0L);
	}
	
	private void assertNoLineItems() {
		Assert.assertTrue(order.getLineItems().isEmpty());
	}

	private void assertTotalCostInCentsIs(Long expectedCost) {
		Assert.assertEquals(expectedCost, order.getTotalCostInCents());
	}
	
	@Test
	public void testOrderWithOneLineItemEnsureCostIsCostOfLineItem() {
		LineItem lineItem = injectLineItemWithCostInCents(100L);
		assertTotalCostInCentsIs(lineItem.getCostInCents());
	}
	
	private LineItem injectLineItemWithCostInCents(Long cost) {
		LineItem lineItem = new LineItem();
		lineItem.setCostInCents(cost);
		order.addLineItem(lineItem);
		return lineItem;
	}
	
	@Test
	public void testOrderWithTwoLineItemsEnsureCostIsCostOfBothLineItem() {
		LineItem lineItem1 = injectLineItemWithCostInCents(100L);
		LineItem lineItem2 = injectLineItemWithCostInCents(150L);
		assertTotalCostInCentsIs(lineItem1.getCostInCents() + lineItem2.getCostInCents());
	}
}
