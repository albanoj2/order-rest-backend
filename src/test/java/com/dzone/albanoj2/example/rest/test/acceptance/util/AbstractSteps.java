package com.dzone.albanoj2.example.rest.test.acceptance.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dzone.albanoj2.example.rest.Application;


@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public abstract class AbstractSteps {

	@Autowired
	private MockMvc mvc;
	
	private MockHttpServletResponse lastGetResponse;
	private MockHttpServletResponse lastPostResponse;
	private MockHttpServletResponse lastPutResponse;
	private MockHttpServletResponse lastDeleteResponse;

	protected void get(String url) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(result -> lastGetResponse = result.getResponse());
	}
	
	protected void post(String url, String body) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> lastPostResponse = result.getResponse());
	}
	
	protected void put(String url, String body) throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> lastPutResponse = result.getResponse());
	}
	
	protected void delete(String url) throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(url)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(result -> lastDeleteResponse = result.getResponse());
	}

	protected MockHttpServletResponse getLastGetResponse() {
		return lastGetResponse;
	}

	protected MockHttpServletResponse getLastPostResponse() {
		return lastPostResponse;
	}

	protected MockHttpServletResponse getLastPutResponse() {
		return lastPutResponse;
	}

	protected MockHttpServletResponse getLastDeleteResponse() {
		return lastDeleteResponse;
	}
}