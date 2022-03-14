package cn.sparrow.permission.mgt.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.mgt.api.OrganizationRestService;
import cn.sparrow.permission.model.organization.Organization;

@WebMvcTest
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	OrganizationRestService organizationRestService;

	EasyRandom generator = new EasyRandom();

	@Test
	public void webTest() throws Exception {
		Organization organization = new Organization("name", "code", OrganizationTypeEnum.ORGANIZATION);
		organization.setId("test");

		when(organizationRestService.create(organization)).thenReturn(organization);
		this.mockMvc
				.perform(post("/organizations").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(organization))).andDo(print()).andExpect(status().isOk());
		
//		when(organizationRestService.create(organization)).thenReturn(organization);
//		MvcResult mvcResult = this.mockMvc
//				.perform(post("/organizations").contentType(MediaType.APPLICATION_JSON)
//						.content(new ObjectMapper().writeValueAsString(organization)))
//				.andExpect(request().asyncStarted()).andReturn();
//
//		this.mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isCreated());

		this.mockMvc
				.perform(delete("/organizations").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(new String[] { "test" })))
				.andDo(print()).andExpect(status().isOk());

	}

}
