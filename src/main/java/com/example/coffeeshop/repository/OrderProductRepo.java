package com.example.coffeeshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeeshop.entity.OrderProduct;
import com.example.coffeeshop.entity.UserProduct;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {
	/**
	 * Method to search user by email id
	 * 
	 * @param emailId
	 * @return User entity with user's details if email id is valid
	 */
	@Transactional
	OrderProduct findByOrderIdAndProductId(Long orderId, Long productId);
}
