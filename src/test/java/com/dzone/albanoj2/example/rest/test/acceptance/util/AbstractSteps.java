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

	protected void get(String url) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
			.andDo(result -> lastGetStatus = result.getResponse().getStatus());
	}
	
	protected int getLastGetStatus() {
		return lastGetStatus;
	}
}