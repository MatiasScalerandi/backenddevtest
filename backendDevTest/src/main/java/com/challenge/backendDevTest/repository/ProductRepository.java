package com.challenge.backendDevTest.repository;

import java.util.List;

import com.challenge.backendDevTest.dto.ProductDto;

public interface ProductRepository {
	List<ProductDto> loadMockJson();
}
