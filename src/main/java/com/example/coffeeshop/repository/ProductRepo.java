package com.example.coffeeshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeeshop.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	/**
	 * Method to search product by product id
	 * 
	 * @param productId
	 * @return Product entity with product's details if product id is valid
	 */
	@Transactional
	Product findById(Long productId);
}
