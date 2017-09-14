package com.dzone.albanoj2.example.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dzone.albanoj2.example.rest.domain.LineItem;
import com.dzone.albanoj2.example.rest.domain.Order;
import com.dzone.albanoj2.example.rest.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

	@Autowired
    private MockMvc mvc;
	
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
        get("/order")
        	.andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));
    }
    
    private void assertNoOrders() {
    	assertOrderCountIs(0);
    }
    
    private void assertOrderCountIs(int count) {
    	Assert.assertEquals(count, repository.getCount());
    }
    
    private ResultActions get(String url) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON));
	}
    
    @Test
    public void testGetAllOneOrderEnsureCorrectResponse() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
        get("/order")
        	.andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].description").value(injectedOrder.getDescription()))
            .andExpect(jsonPath("$.[0].totalCostInCents").value(0));
    }
    
    @Test
    public void testGetAllOneOrderEnsureCorrectLinks() throws Exception {
    	Order injectedOrder = injectOrder();
    	assertOrderCountIs(1);
        get("/order")
        	.andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].links.[?(@['rel']==\"self\")].href").value(entityLinks.linkForSingleResource(injectedOrder).toString()))
            .andExpect(jsonPath("$.[0].links.[?(@['rel']==\"lineItems\")].href").value(entityLinks.linkFor(LineItem.class, injectedOrder.getId()).toString()));
    }
    
    private Order injectOrder() {
    	Order order = new Order();
    	order.setDescription("Test description");
    	
    	return repository.create(order);
    }
}
