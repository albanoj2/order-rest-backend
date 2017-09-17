package com.dzone.albanoj2.example.rest.test.integration.controller.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.ResultMatcher;

public class ControllerTestUtils {
	
	private static final String SELF_REL = "self";
	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";

	public static ResultMatcher selfLinkAtIndexIs(int index, String expected) {
		return linkAtIndexIs(index, SELF_REL, expected);
	}
	
	private static ResultMatcher linkAtIndexIs(int index, String linkKey, String expected) {
		return jsonPath("$.[" + index + "]._links." + linkKey + ".href").value(expected);
	}
	
	public static ResultMatcher selfLinkIs(String expected) {
		return linkIs(SELF_REL, expected);
	}
	
	public static ResultMatcher linkIs(String linkKey, String expected) {
		return jsonPath("$._links." + linkKey + ".href").value(expected);
	}

	public static ResultMatcher updateLinkAtIndexIs(int index, String expected) {
		return linkAtIndexIs(index, UPDATE_REL, expected);
	}
	
	public static ResultMatcher updateLinkIs(String expected) {
		return linkIs(UPDATE_REL, expected);
	}

	public static ResultMatcher deleteLinkAtIndexIs(int index, String expected) {
		return linkAtIndexIs(index, DELETE_REL, expected);
	}
	
	public static ResultMatcher deleteLinkIs(String expected) {
		return linkIs(DELETE_REL, expected);
	}
}
