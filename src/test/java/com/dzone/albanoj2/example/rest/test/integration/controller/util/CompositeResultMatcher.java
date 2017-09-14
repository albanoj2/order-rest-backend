package com.dzone.albanoj2.example.rest.test.integration.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

public class CompositeResultMatcher implements ResultMatcher {
	
	private List<ResultMatcher> matchers = new ArrayList<>();

	@Override
	public void match(MvcResult result) throws Exception {

		for (ResultMatcher matcher: matchers) {
			matcher.match(result);
		}
	}
	
	public CompositeResultMatcher addMatcher(ResultMatcher matcher) {
		matchers.add(matcher);
		return this;
	}

}
