package com.dzone.albanoj2.example.rest.test.integration.controller;

import static com.dzone.albanoj2.example.rest.test.integration.controller.util.OrderControllerTestUtils.*;
import static com.dzone.albanoj2.example.rest.test.util.OrderTestUtils.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.dzone.albanoj2.example.rest.domain.Order;
import com.dzone.albanoj2.example.rest.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest extends ControllerIntegrationTest {
	
	private static final String INVALID_TEST_ORDER = "";
	private static final String TEST_ORDER = "{\"description\": \"Some test description\", \"costInCents\": 200}";
	private static final String TEST_ORDER_MISSING_ORDER_DATA = "{\"foo\": \"bar\"}";
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private EntityLinks entityLinks;
	
	@Before
	public void setUp() {
		repository.clear();
	}

    @Test
    public void testGetAllEmptyListEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
        getOrder()
        	.andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));
    }

	private ResultActions getOrder() throws Exception {
		return get("/order");
	}
    
    private void assertNoOrders() {
    	assertOrderCountIs(0);
    }
    
    private void assertOrderCountIs(int count) {
    	Assert.assertEquals(count, repository.getCount());
    }
    
    @Test
    public void testGetAllOneOrderEnsureCorrectResponse() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
        getOrder()
        	.andExpect(status().isOk())
        	.andExpect(orderAtIndexIsCorrect(0, injectedOrder));
    }
    
    @Test
    public void testGetAllOneOrderEnsureCorrectLinks() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
        getOrder()
        	.andExpect(status().isOk())
        	.andExpect(orderLinksAtIndexAreCorrect(0, injectedOrder, entityLinks));
    }
    
    private Order injectOrder() {
    	Order order = new Order();
    	order.setDescription("Test description");
    	
    	return repository.create(order);
    }
    
    @Test
    public void testGetAllTwoOrderEnsureCorrectResponse() throws Exception {
    	Order injectedOrder1 = injectOrder();
    	Order injectedOrder2 = injectOrder();
    	assertOrderCountIs(2);
        getOrder()
        	.andExpect(status().isOk())
        	.andExpect(orderAtIndexIsCorrect(0, injectedOrder1))
        	.andExpect(orderAtIndexIsCorrect(1, injectedOrder2));
    }
    
    @Test
    public void testGetAllTwoOrderEnsureCorrectLinks() throws Exception {
    	Order injectedOrder1 = injectOrder();
    	Order injectedOrder2 = injectOrder();
    	assertOrderCountIs(2);
    	getOrder()
	    	.andExpect(status().isOk())
	    	.andExpect(orderLinksAtIndexAreCorrect(0, injectedOrder1, entityLinks))
	    	.andExpect(orderLinksAtIndexAreCorrect(1, injectedOrder2, entityLinks));
    }
    
    @Test
    public void testGetNonexistentOrderEnsureNotFoundResponse() throws Exception {
    	assertNoOrders();
        getOrder(1)
        	.andExpect(status().isNotFound());
    }

	private ResultActions getOrder(long id) throws Exception {
		return get("/order/{id}", id);
	}
    
    @Test
    public void testGetExistingOrderEnsureCorrectResponse() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
        getOrder(injectedOrder.getId())
        	.andExpect(status().isOk())
        	.andExpect(orderIsCorrect(injectedOrder));
    }
    
    @Test
    public void testGetExistingOrderEnsureCorrectLinks() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
    	getOrder(injectedOrder.getId())
        	.andExpect(status().isOk())
        	.andExpect(orderLinksAreCorrect(injectedOrder, entityLinks));
    }
    
    @Test
    public void testCreateNewOrderEnsureOrderCreated() throws Exception {
    	assertNoOrders();
    	Order desiredOrder = generateTestOrder();
    	createOrder(toJsonString(desiredOrder));
    	assertOrderCountIs(1);
    	assertAllButIdsMatchBetweenOrders(desiredOrder, getCreatedOrder());
    }
    
    private ResultActions createOrder(String payload) throws Exception {
    	return post("/order", payload);
    }

	private Order getCreatedOrder() {
		List<Order> orders = repository.findAll();
		return orders.get(orders.size() - 1);
	}
    
    @Test
    public void testCreateNewOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	createOrder(TEST_ORDER)
    		.andExpect(status().isCreated())
    		.andExpect(orderIsCorrect(getCreatedOrder()));
    }
    
    @Test
    public void testCreateNewOrderEnsureCorrectLinks() throws Exception {
    	assertNoOrders();
    	createOrder(TEST_ORDER)
    		.andExpect(status().isCreated())
    		.andExpect(orderLinksAreCorrect(getCreatedOrder(), entityLinks));
    }
    
    @Test
    public void testCreateNewOrderMissingDataEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	createOrder(TEST_ORDER_MISSING_ORDER_DATA)
    		.andExpect(status().isCreated())
    		.andExpect(orderIsCorrect(getCreatedOrder()));
    }
    
    @Test
    public void testCreateInvalidNewOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	createOrder(INVALID_TEST_ORDER)
    		.andExpect(status().isBadRequest());
    }
    
    @Test
    public void testDeleteNonexistentOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	deleteOrder(1)
    		.andExpect(status().isNotFound());
    }
    
    private ResultActions deleteOrder(long id) throws Exception {
    	return delete("/order/{id}", id);
    }

    @Test
    public void testDeleteExistingOrderEnsureCorrectResponse() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
    	deleteOrder(injectedOrder.getId())
    		.andExpect(status().isNoContent());
    }
    
    @Test
    public void testDeleteExistingOrderEnsureOrderDeleted() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
    	deleteOrder(injectedOrder.getId());
    	assertNoOrders();
    }
    
    @Test
    public void testUpdateNonexistentOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	updateOrder(1, new Order())
    		.andExpect(status().isNotFound());
    }
    
    private ResultActions updateOrder(long id, Order updatedOrder) throws Exception {
    	return put("/order/{id}", updatedOrder, String.valueOf(id));
    }
    
    @Test
    public void testUpdateExistingOrderEnsureOrderUpdated() throws Exception {
    	Order originalOrder = injectOrder();
    	assertOrderCountIs(1);
    	Order updatedOrder = generateUpdatedOrder(originalOrder);
    	updateOrder(originalOrder.getId(), updatedOrder);
    	assertAllButIdsMatchBetweenOrders(updatedOrder, originalOrder);
    }
    
    @Test
    public void testUpdateExistingOrderEnsureCorrectResponse() throws Exception {
    	Order originalOrder = injectOrder();
    	assertOrderCountIs(1);
    	Order updatedOrder = generateUpdatedOrder(originalOrder);
    	updateOrder(originalOrder.getId(), updatedOrder)
    		.andExpect(status().isOk())
    		.andExpect(updatedOrderIsCorrect(originalOrder.getId(), updatedOrder));
    }
}
