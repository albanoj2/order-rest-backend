package com.dzone.albanoj2.example.rest.test.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
public class ControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;
	
	protected ResultActions get(String url, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.get(url, urlVariables).accept(MediaType.APPLICATION_JSON));
	}
	
	protected ResultActions post(String url, String content, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
		);
	}
	
	protected ResultActions delete(String url, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.delete(url, urlVariables).accept(MediaType.APPLICATION_JSON));
	}
	
	protected ResultActions put(String url, Object content, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.put(url, urlVariables)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJsonString(content))
		);
	}
	
	protected static String toJsonString(final Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
