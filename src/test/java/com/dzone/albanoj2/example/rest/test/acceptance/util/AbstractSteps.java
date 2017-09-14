package com.dzone.albanoj2.example.rest.test.acceptance.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
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
	
	private int lastGetStatus;
	private int lastPostStatus;
	private int lastPutStatus;
	private int lastDeleteStatus;

	protected void get(String url) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(result -> lastGetStatus = result.getResponse().getStatus());
	}
	
	protected void post(String url, String body) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> lastPostStatus = result.getResponse().getStatus());
	}
	
	protected void put(String url, String body) throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andDo(result -> lastPutStatus = result.getResponse().getStatus());
	}
	
	protected void delete(String url) throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(url)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(result -> lastDeleteStatus = result.getResponse().getStatus());
	}
	
	protected int getLastGetStatus() {
		return lastGetStatus;
	}
	
	protected int getLastPostStatus() {
		return lastPostStatus;
	}

	public int getLastPutStatus() {
		return lastPutStatus;
	}

	public int getLastDeleteStatus() {
		return lastDeleteStatus;
	}
}