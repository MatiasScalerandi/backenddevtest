package com.challenge.backendDevTest.service;

import java.text.ParseException;
import java.util.List;

import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.exception.ProductNotFoundException;
import com.challenge.backendDevTest.model.Product;

public interface ProductService {
	List<ProductDto> getAllProducts() throws ProductNotFoundException;
	List<ProductDto> getProductsByNameAndAvailability(String name) throws ParseException, ProductNotFoundException;
	List<ProductDto> getProductsByOrderByPrice() throws ProductNotFoundException;
}
