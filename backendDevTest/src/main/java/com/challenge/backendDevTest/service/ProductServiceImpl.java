package com.challenge.backendDevTest.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.challenge.backendDevTest.constant.ErrorConstants;
import com.challenge.backendDevTest.dto.ProductDto;
import com.challenge.backendDevTest.exception.ProductNotFoundException;
import com.challenge.backendDevTest.model.Product;
import com.challenge.backendDevTest.repository.ProductDaoRepository;
import com.challenge.backendDevTest.repository.ProductRepository;
import com.challenge.backendDevTest.util.ModelMapperProductUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository mockRepo;

	@Autowired
	private ProductDaoRepository dao;

	private static ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ProductDto> getAllProducts() throws ProductNotFoundException {
		try {
			List<ProductDto> listMock = mockRepo.loadMockJson();
			if (listMock.isEmpty()) {
				throw new ProductNotFoundException(ErrorConstants.LIST_JSON_EMPTY);
			}

			postDataInDB(listMock);

			return listMock;
		} catch (ParseException e) {
			throw new ProductNotFoundException(ErrorConstants.ERROR_PARSING_PRODUCT);
		} catch (Exception e) {
			throw new ProductNotFoundException(ErrorConstants.ERROR_LOOKING_PRODUCTS);
		}
	}

	private void postDataInDB(List<ProductDto> listMock) throws ParseException {
		for (ProductDto productDto : listMock) {
			Product productDao = ModelMapperProductUtil.convertToEntity(productDto);
			List<Product> db = dao.findAll();

			boolean noExist = false;
			noExist = db.size() < listMock.size() ? true : false;
			if (noExist) {
				dao.save(productDao);
			}

		}
	}

	@Override
	public List<ProductDto> getProductsByNameAndAvailability(String name)
			throws ParseException, ProductNotFoundException {

		List<ProductDto> list = null;

		try {
			list = getAllProducts();

		} catch (ProductNotFoundException e) {
			throw new ProductNotFoundException(ErrorConstants.ERROR_LOOKING_PRODUCTS);
		}
		return list.stream().filter(l -> l.getName() != null && l.getName().equalsIgnoreCase(name))
				.filter(a -> a.isAvailability() == true).collect(Collectors.toList());

	}

	@Override
	public List<ProductDto> getProductsByOrderByPrice() {

		List<ProductDto> listDto = new ArrayList<>();

		List<Product> listSorted = dao.findAll(Sort.by(Sort.Direction.DESC, "price"));
		if (listSorted.isEmpty()) {
			return listDto;
		}
		for (Product product : listSorted) {
			if (product != null) {
				listSorted.forEach(l -> listDto.add(modelMapper.map(l, ProductDto.class)));
				break;
			}
		}

		return listDto;
	}

}
