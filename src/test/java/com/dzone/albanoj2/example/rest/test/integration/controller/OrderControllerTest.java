package com.dzone.albanoj2.example.rest.test.integration.controller;

import static com.dzone.albanoj2.example.rest.test.integration.controller.util.OrderControllerTestUtils.*;
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
	private static final String TEST_ORDER = "{\"description\": \"Some test description\", \"lineItems\": []}";
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
    public void testPostNewOrderEnsureOrderCreated() throws Exception {
    	assertNoOrders();
    	postOrder(TEST_ORDER);
    	assertOrderCountIs(1);
    }
    
    private ResultActions postOrder(String payload) throws Exception {
    	return post("/order", payload);
    }
    
    @Test
    public void testPostNewOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	postOrder(TEST_ORDER)
    		.andExpect(status().isCreated())
    		.andExpect(orderIsCorrect(getCreatedOrder()));
    }

	private Order getCreatedOrder() {
		List<Order> orders = repository.findAll();
		return orders.get(orders.size() - 1);
	}
    
    @Test
    public void testPostNewOrderEnsureCorrectLinks() throws Exception {
    	assertNoOrders();
    	postOrder(TEST_ORDER)
    		.andExpect(status().isCreated())
    		.andExpect(orderLinksAreCorrect(getCreatedOrder(), entityLinks));
    }
    
    @Test
    public void testPostNewOrderMissingDataEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	postOrder(TEST_ORDER_MISSING_ORDER_DATA)
    		.andExpect(status().isCreated())
    		.andExpect(orderIsCorrect(getCreatedOrder()));
    }
    
    @Test
    public void testPostInvalidNewOrderEnsureCorrectResponse() throws Exception {
    	assertNoOrders();
    	postOrder(INVALID_TEST_ORDER)
    		.andExpect(status().isBadRequest());
    }
}
