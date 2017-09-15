package com.dzone.albanoj2.example.rest.test.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dzone.albanoj2.example.rest.domain.IllegalOrderStateException;
import com.dzone.albanoj2.example.rest.domain.Order;


public class OrderTest {
	
	private Order order;
	
	@Before
	public void setUp() {
		order = new Order();	
	}

	@Test(expected = IllegalOrderStateException.class)
	public void testMarkAsDeliveredWithoutMarkingAsShippedEnsureIllegalOrderStateExceptionThrown() throws Exception {
		order.markAsUnshipped();
		order.markAsDelivered();
	}
	
	@Test(expected = IllegalOrderStateException.class)
	public void testMarkAsUnshippedAfterDeliveredEnsureIllegalOrderStateExceptionThrown() throws Exception {
		order.markAsShipped();
		order.markAsDelivered();
		order.markAsUnshipped();
	}
	
	@Test
	public void testMarkAsShippedThenMarkDeliveredEnsureNoExceptionThrown() throws Exception {
		order.markAsShipped();
		order.markAsDelivered();
	}
	
	@Test
	public void testIsCompletedWhenShippedAndDeliveredEnsureTrue() throws Exception {
		order.markAsShipped();
		order.markAsDelivered();
		Assert.assertTrue(order.isCompleted());
	}
	
	@Test
	public void testIsCompletedWhenShippedAndNotDeliveredEnsureFalse() throws Exception {
		order.markAsShipped();
		order.markAsNotDelivered();
		Assert.assertFalse(order.isCompleted());
	}
	
	@Test
	public void testIsCompletedWhenUnshippedAndNotDeliveredEnsureFalse() throws Exception {
		order.markAsUnshipped();
		order.markAsNotDelivered();
		Assert.assertFalse(order.isCompleted());
	}
}
