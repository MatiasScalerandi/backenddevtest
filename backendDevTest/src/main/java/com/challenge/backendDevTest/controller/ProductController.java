package com.challenge.backendDevTest.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.backendDevTest.constant.ErrorConstants;
import com.challenge.backendDevTest.dto.ErrorDto;
import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.exception.ProductNotFoundException;
import com.challenge.backendDevTest.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api")
public class ProductController {

	@Autowired
	private ProductService service;

	@ApiOperation(notes = "returns the full product list", value = "ProductDto", nickname = "Product")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok.", response = ProductDto.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERROR_LOOKING_PRODUCTS, response = ProductNotFoundException.class) })
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getProducts() throws ProductNotFoundException {
		List<ProductDto> response = service.getAllProducts();
		return ResponseEntity.ok(response);
	}

	@ApiOperation(notes = "Filter the list of products by name and availability", value = "ProductDto", nickname = "Product")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok.", response = ProductDto.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERROR_LOOKING_PRODUCTS, response = ProductNotFoundException.class) })
	@GetMapping("/filter")
	public ResponseEntity<?> getProductsByNameAndAvilability(
			@ApiParam(name = "name", value = "Product name", required = true) @Nullable @RequestParam String name)
			throws ParseException, ProductNotFoundException {

		List<ProductDto> response = service.getProductsByNameAndAvailability(name);
		return ResponseEntity.ok(response);
	}

	@ApiOperation(notes = "Sort the list by price order desc", value = "ProductDto", nickname = "Product")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok.", response = ProductDto.class),
			@ApiResponse(code = 400, message = ErrorConstants.ERROR_LOOKING_PRODUCTS, response = ProductNotFoundException.class) })
	@GetMapping("/orderByPrice")
	public ResponseEntity<?> getProductsByOrderByPrice() throws ProductNotFoundException {
		List<ProductDto> response = service.getProductsByOrderByPrice();
		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> productHandler(ProductNotFoundException e) {
		ErrorDto error = new ErrorDto();
		error.setName(ErrorConstants.ERROR_HANDLER);
		error.setDescription(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
