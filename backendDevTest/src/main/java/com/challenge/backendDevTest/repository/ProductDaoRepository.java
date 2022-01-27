package com.challenge.backendDevTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.backendDevTest.model.Product;

public interface ProductDaoRepository extends JpaRepository<Product, Long> {

}
