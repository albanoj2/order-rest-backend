package com.dzone.albanoj2.example.rest.test.integration.controller.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.ResultMatcher;

public class ControllerTestUtils {
	
	public static ResultMatcher selfLinkAtIndexIs(int index, String expected) {
		return jsonPath("$.[" + index + "]._links.self.href").value(expected);
	}
	
	public static ResultMatcher lineItemLinkAtIndexIs(int index, String expected) {
		return jsonPath("$.[" + index + "]._links.lineItems.href").value(expected);
	}
	
	public static ResultMatcher selfLinkIs(String expected) {
		return jsonPath("$._links.self.href").value(expected);
	}
	
	public static ResultMatcher lineItemLinkIs(String expected) {
		return jsonPath("$._links.lineItems.href").value(expected);
	}
}
