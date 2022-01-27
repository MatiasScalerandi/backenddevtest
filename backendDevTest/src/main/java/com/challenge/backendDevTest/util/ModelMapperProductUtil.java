package com.challenge.backendDevTest.util;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.model.Product;

public class ModelMapperProductUtil {

	private static ModelMapper modelMapper = new ModelMapper();

	public static Product convertToEntity(ProductDto productDto) throws ParseException {
		Product product = modelMapper.map(productDto, Product.class);
		return product;
	}

}
