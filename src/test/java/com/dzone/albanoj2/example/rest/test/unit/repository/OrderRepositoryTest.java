package com.dzone.albanoj2.example.rest.test.unit.repository;

import static com.dzone.albanoj2.example.rest.test.util.OrderTestUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dzone.albanoj2.example.rest.domain.Order;
import com.dzone.albanoj2.example.rest.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
	
	private static final long NONEXISTENT_ID = 1000;

	@Autowired
	private OrderRepository repository;
	
	@Before
	public void setUp() {
		repository.clear();
	}
	
	@Test
	public void testFindNonexistentOrderEnsureOptionalIsNotPresent() throws Exception {
		assertNoExistingOrders();
		Optional<Order> order = repository.findById(NONEXISTENT_ID);
		Assert.assertFalse(order.isPresent());
	}

	private void assertNoExistingOrders() {
		assertExistingOrderCountIs(0);
	}
	
	private void assertExistingOrderCountIs(int count) {
		Assert.assertEquals(count, repository.getCount());
	}
	
	@Test
	public void testFindExistingOrderEnsureOptionalIsPresent() throws Exception {
		Order injectedOrder = injectOrder();
		Optional<Order> foundOrder = repository.findById(injectedOrder.getId());
		Assert.assertTrue(foundOrder.isPresent());
	}
	
	private Order injectOrder() {
		Order createdOrder = repository.create(generateTestOrder());
		return createdOrder;
	}
	
	@Test
	public void testFindExistingOrderEnsureCorrectOrderValues() throws Exception {
		Order injectedOrder = injectOrder();
		Optional<Order> foundOrder = repository.findById(injectedOrder.getId());
		assertOrdersMatch(injectedOrder, foundOrder.get());
	}
	
	private static void assertOrdersMatch(Order expected, Order actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		assertAllButIdsMatchBetweenOrders(expected, actual);
	}
	
	@Test
	public void testFindAllWithNoExistingOrdersEnsureNoOrdersFound() throws Exception {
		assertFindAllIsCorrectWithOrderCount(0);
	}
	
	private void assertFindAllIsCorrectWithOrderCount(int count) {
		injectGivenNumberOfOrders(count);
		assertExistingOrderCountIs(count);
		List<Order> ordersFound = repository.findAll();
		Assert.assertEquals(count, ordersFound.size());
	}
	
	private List<Order> injectGivenNumberOfOrders(int count) {
		
		List<Order> injectedOrders = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			injectedOrders.add(injectOrder());
		}
		
		return injectedOrders;
	}
	
	@Test
	public void testFindAllWithOneExistingOrdersEnsureOneOrdersFound() throws Exception {
		assertFindAllIsCorrectWithOrderCount(1);
	}
	
	@Test
	public void testFindAllWithTwoExistingOrdersEnsureTwoOrdersFound() throws Exception {
		assertFindAllIsCorrectWithOrderCount(2);
	}
	
	@Test
	public void testFindAllWithTwoExistingOrderEnsureFirstOrderIsCorrect() throws Exception {
		List<Order> injectedOrders = injectGivenNumberOfOrders(2);
		List<Order> ordersFound = repository.findAll();
		assertOrdersMatch(injectedOrders.get(0), ordersFound.get(0));
	}

	@Test
	public void testFindAllWithTwoExistingOrderEnsureSecondOrderIsCorrect() throws Exception {
		List<Order> injectedOrders = injectGivenNumberOfOrders(2);
		List<Order> ordersFound = repository.findAll();
		assertOrdersMatch(injectedOrders.get(1), ordersFound.get(1));
	}
	
	@Test
	public void testDeleteNonexistentOrderEnsureNoOrderDeleted() throws Exception {
		assertNoExistingOrders();
		boolean wasDeleted = repository.delete(NONEXISTENT_ID);
		Assert.assertFalse(wasDeleted);
	}

	@Test
	public void testDeleteExistingOrderEnsureOrderDeleted() throws Exception {
		Order injectedOrder = injectOrder();
		assertExistingOrderCountIs(1);
		boolean wasDeleted = repository.delete(injectedOrder.getId());
		Assert.assertTrue(wasDeleted);
		assertNoExistingOrders();
	}
	
	@Test
	public void testUpdateNonexistentOrderEnsureNoUpdateMade() throws Exception {
		assertNoExistingOrders();
		boolean wasUpdated = repository.update(NONEXISTENT_ID, new Order());
		Assert.assertFalse(wasUpdated);
	}
	
	@Test
	public void testUpdateExistingOrderEnsureUpdateMade() throws Exception {
		Order injectedOrder = injectOrder();
		boolean wasUpdated = repository.update(injectedOrder.getId(), new Order());
		Assert.assertTrue(wasUpdated);
	}
	
	@Test
	public void testUpdateExistingOrderEnsureOriginalOrderIsUpdated() throws Exception {
		Order originalOrder = injectOrder();
		Order updatedOrder = generateUpdatedOrder(originalOrder);
		repository.update(originalOrder.getId(), updatedOrder);
		assertAllButIdsMatchBetweenOrders(updatedOrder, originalOrder);
	}
	
	@Test
	public void testUpdateExistingOrderWithNullUpdatedOrderEnsureNoUpdateMade() throws Exception {
		Order injectedOrder = injectOrder();
		boolean wasUpdated = repository.update(injectedOrder.getId(), null);
		Assert.assertFalse(wasUpdated);
	}
}
