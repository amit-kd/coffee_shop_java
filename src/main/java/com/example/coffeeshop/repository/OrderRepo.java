package com.example.coffeeshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeeshop.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	/**
	 * Method to search product by product id
	 * 
	 * @param productId
	 * @return Product entity with product's details if product id is valid
	 */
	@Transactional
	Order findById(Long orderId);
}
