package com.dzone.albanoj2.example.rest.test.acceptance.step;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.dzone.albanoj2.example.rest.repository.OrderRepository;
import com.dzone.albanoj2.example.rest.test.acceptance.util.AbstractSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderSteps extends AbstractSteps {
	
	@Autowired
	private OrderRepository orders;
	
	@When("^the client calls /order$")
	public void theClientCallsGetOrders() throws Throwable{
		get("/order");
	}

	@And("^no orders are present$")
	public void noOrdersArePresent() throws Throwable {
		givenNumberOfOrdersArePresent(0);
	}
	
	@And("^(\\d+) orders are present$")
	public void givenNumberOfOrdersArePresent(int count) throws Throwable {
		Assert.assertEquals(count, orders.getCount());
	}
	 
	@Then("^the client receives status code of (\\d+)$")
	public void theClientReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastGetStatus());
	}
}
