package com.challenge.backendDevTest.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.challenge.backendDevTest.dto.ProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

	List<ProductDto> products;
	
	@Override
	public List<ProductDto> loadMockJson() {

		File file = null;
		
		try {
			file = ResourceUtils.getFile("classpath:products.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ObjectMapper om = new ObjectMapper();
		TypeReference<List<ProductDto>> tr = new TypeReference<List<ProductDto>>() {};
		List<ProductDto> productDto = null;
		try {
			productDto = om.readValue(file,  tr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productDto;
	}

}
