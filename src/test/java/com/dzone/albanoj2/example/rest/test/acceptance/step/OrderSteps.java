package com.dzone.albanoj2.example.rest.test.acceptance.step;

import java.util.Map;

import org.junit.Assert;

import com.dzone.albanoj2.example.rest.test.acceptance.util.AbstractSteps;
import com.fasterxml.jackson.core.type.TypeReference;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderSteps extends AbstractSteps {
	
	private static final String TEST_ORDER = "{\"description\": \"some test order\", \"lineItems\": [{\"name\": \"test item 1\", \"description\": \"some test item 1\", \"costInCents\": 100}, {\"name\": \"test item 2\", \"description\": \"some test item 2\", \"costInCents\": 200}]}";
	private static final TypeReference<Map<String, Object>> RESOURCE_TYPE = new TypeReference<Map<String, Object>>() {};
	
	@When("^the user creates an order$")
	public void theUserCallsGetOrders() throws Throwable {
		post("/order", TEST_ORDER);
	}
	
	@And("^the order is successfully created$")
	public void theOrderIsSuccessfullyCreated() {
		 Assert.assertEquals(201, getLastPostResponse().getStatus());
	}
	
	@And("^the user retrieves the created order$")
	public void theUserRetrievesTheOrder() throws Throwable {
		get("/order/{id}", getLastPostContentAs(RESOURCE_TYPE).get("id"));
	}
	
	@Then("^the user receives status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastGetResponse().getStatus());
	}
	
	@And("^the retrieved order is correct$")
	public void theRetrievedOrderIsCorrect() throws Throwable {
		assertOrderResourcesMatch(getLastPostContentAs(RESOURCE_TYPE), getLastGetContentAs(RESOURCE_TYPE));
	}
	
	private static void assertOrderResourcesMatch(Map<String, Object> expected, Map<String, Object> actual) {
		Assert.assertEquals(expected.size(), actual.size());
		
		for (String key: expected.keySet()) {
			Assert.assertEquals(expected.get(key), actual.get(key));
		}
	}
}
