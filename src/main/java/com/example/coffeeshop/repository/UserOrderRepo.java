package com.example.coffeeshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeeshop.entity.UserOrder;

@Repository
public interface UserOrderRepo extends JpaRepository<UserOrder, Long> {
	/**
	 * Method to search user by email id
	 * 
	 * @param emailId
	 * @return User entity with user's details if email id is valid
	 */
	@Transactional
	UserOrder findByUserIdAndOrderId(Long userId, Long orderId);
}
