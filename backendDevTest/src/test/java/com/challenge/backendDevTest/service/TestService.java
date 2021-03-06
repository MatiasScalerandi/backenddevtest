package com.challenge.backendDevTest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.exception.ProductNotFoundException;
import com.challenge.backendDevTest.model.Product;
import com.challenge.backendDevTest.repository.ProductDaoRepository;
import com.challenge.backendDevTest.repository.ProductRepository;
import com.challenge.backendDevTest.repository.ProductRepositoryImpl;

public class TestService {

	@Mock
	private ProductDaoRepository productDaoRepository;

	@Mock
	private ProductRepository productRepoMock;

	@Autowired
	@InjectMocks
	private ProductServiceImpl service;
	List<Product> mockList = new ArrayList<>();
	List<ProductDto> mockListDto = new ArrayList<>();
	Product p = new Product();
	ProductDto pDto = new ProductDto();
	ProductDto pDto2 = new ProductDto();

	@InjectMocks
	private ProductRepositoryImpl repo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		p.setId(1L);
		p.setName("shirt");
		p.setAvailability(true);
		p.setPrice(10.00);

		pDto.setId(1L);
		pDto.setName("shirt");
		pDto.setAvailability(true);
		pDto.setPrice(10.00);
		
		pDto2.setId(1L);
		pDto2.setName("shirt");
		pDto2.setAvailability(true);
		pDto2.setPrice(10.00);
		
		mockListDto.add(pDto);
		mockListDto.add(pDto2);
		
		
		
	}

	@Test
	public void OrderByPriceListEmpty() {
		List<Product> lp = new ArrayList<>();

		Mockito.when(productDaoRepository.findAll()).thenReturn(lp);
		service.getProductsByOrderByPrice();

	}

	@Test
	public void LoadMockTest() {
		List<ProductDto> lp = new ArrayList<>();

		Mockito.when(productRepoMock.loadMockJson()).thenReturn(lp);
		repo.loadMockJson();

	}

	@Test
	public void OrderByPriceOk() {
		List<Product> lp = new ArrayList<>();

		Mockito.when(productDaoRepository.findAll(Sort.by(Sort.Direction.DESC, "price"))).thenReturn(Arrays.asList(p));
		service.getProductsByOrderByPrice();

	}

	@Test
	public void testGetAllProductsProductNotFound() throws ParseException, ProductNotFoundException {
		List<ProductDto> lp = new ArrayList<>();

		Mockito.when(productRepoMock.loadMockJson()).thenReturn(lp);
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);

		assertThrows(ProductNotFoundException.class, () -> {
			service.getAllProducts();
		});

	}

	@Test
	public void testGetAllProductsOk() throws ParseException, ProductNotFoundException {
		
		Mockito.when(productRepoMock.loadMockJson()).thenReturn(mockListDto);
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);

		service.getAllProducts();

	}
	
	@Test
	public void testGetAllProductsAlreadyExistInDB() throws ParseException, ProductNotFoundException {
		
		Mockito.when(productRepoMock.loadMockJson()).thenReturn(Arrays.asList(pDto));
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);

		service.getAllProducts();

	}
	
	@Test
	public void testGetProductsByNameAndAvilability() throws ParseException, ProductNotFoundException {
		
		Mockito.when(productRepoMock.loadMockJson()).thenReturn(mockListDto);
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);
		
		String name = "shirt";

		service.getProductsByNameAndAvailability(name);

	}
	
	@Test
	public void testGetProductsByNameAndAvilabilityAlreadyExistInDB() throws ParseException, ProductNotFoundException {
		
		Mockito.when(productRepoMock.loadMockJson()).thenReturn(Arrays.asList(pDto));
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);
		
		String name = "shirt";

		service.getProductsByNameAndAvailability(name);

	}
	
	@Test
	public void testGetProductsByNameAndAvilabilityProductNotFound() throws ParseException, ProductNotFoundException {
		
		List<ProductDto> lp = new ArrayList<>();

		Mockito.when(productRepoMock.loadMockJson()).thenReturn(lp);
		Mockito.when(productDaoRepository.findAll()).thenReturn(Arrays.asList(p));
		Mockito.when(productDaoRepository.save(p)).thenReturn(p);
		
		String name = "short";

		assertThrows(ProductNotFoundException.class, () -> {
			service.getProductsByNameAndAvailability(name);
		});


	}
	
}
