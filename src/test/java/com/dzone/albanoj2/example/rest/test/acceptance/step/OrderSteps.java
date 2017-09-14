package com.dzone.albanoj2.example.rest.test.acceptance.step;

import com.dzone.albanoj2.example.rest.test.acceptance.util.AbstractSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderSteps extends AbstractSteps {

	@When("^the client calls /order$")
	public void theClientCallsGetOrders() throws Throwable{
//	    executeGet("http://localhost:8080/order");
	}
	
	@And("^no orders are present$")
	public void noOrdersArePresent() throws Throwable {
//		Assert.assertEquals(0, orders.getCount());
	}
	 
	@Then("^the client receives status code of (\\d+)$")
	public void theClientReceivesStatusCodeOf(int statusCode) throws Throwable {
//		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
//        Assert.assertEquals(statusCode, currentStatusCode.value());
	}
}
