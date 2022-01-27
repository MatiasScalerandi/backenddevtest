package com.challenge.backendDevTest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.service.ProductService;

@WebMvcTest(ProductController.class)
public class TestController {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService service;


	@Test
	void testGetAllProducts() throws Exception {

		List<ProductDto> list = new ArrayList<>();
		when(service.getAllProducts()).thenReturn(list);

		mvc.perform(get("/api/getAllProducts").contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}

	@Test
	void testProductsByNameAndAvilability() throws Exception {

		List<ProductDto> list = new ArrayList<>();
		String name = "shirt";
		when(service.getProductsByNameAndAvailability(name)).thenReturn(list);

		mvc.perform(get("/api/filter").contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}

	@Test
	void testOrderByPriceDesc() throws Exception {

		List<ProductDto> list = new ArrayList<>();
		when(service.getProductsByOrderByPrice()).thenReturn(list);

		mvc.perform(get("/api/orderByPrice").contentType(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk());

	}

}
