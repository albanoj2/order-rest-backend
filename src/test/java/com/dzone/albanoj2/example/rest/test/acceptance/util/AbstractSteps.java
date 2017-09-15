package com.dzone.albanoj2.example.rest.test.acceptance.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dzone.albanoj2.example.rest.Application;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public abstract class AbstractSteps {
	
	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;
	
	private MockHttpServletResponse lastGetResponse;
	private MockHttpServletResponse lastPostResponse;
	private MockHttpServletResponse lastPutResponse;
	private MockHttpServletResponse lastDeleteResponse;
	private int lastStatusCode;

	protected void get(String url, Object... urlVariable) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url, urlVariable)
				.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(result -> {
				lastGetResponse = result.getResponse();
				lastStatusCode = lastGetResponse.getStatus();
			});
	}
	
	protected void post(String url, String body, Object... urlVariables) throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> {
				lastPostResponse = result.getResponse();
				lastStatusCode = lastPostResponse.getStatus();
			});
	}
	
	protected void put(String url, String body) throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> {
				lastPutResponse = result.getResponse();
				lastStatusCode = lastPutResponse.getStatus();
			});
	}
	
	protected void delete(String url, Object... urlVariables) throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(result -> {
				lastDeleteResponse = result.getResponse();
				lastStatusCode = lastDeleteResponse.getStatus();
			});
	}

	protected MockHttpServletResponse getLastGetResponse() {
		return lastGetResponse;
	}
	
	protected <T> T getLastGetContentAs(TypeReference<T> type) throws Exception {
		return deserializeResponse(getLastGetResponse(), type);
	}
	
	protected <T> T getLastPostContentAs(TypeReference<T> type) throws Exception {
		return deserializeResponse(getLastPostResponse(), type);
	}
	
	private static <T> T deserializeResponse(MockHttpServletResponse response, TypeReference<T> type) throws Exception {
		return deserialize(response.getContentAsString(), type);
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
	
	protected static <T> T deserialize(String json, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, type);
	}
	
	protected static <T> T deserialize(String json, TypeReference<T> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, type);
	}

	public int getLastStatusCode() {
		return lastStatusCode;
	}
}