package com.example.coffeeshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeeshop.entity.UserProduct;

@Repository
public interface UserProductRepo extends JpaRepository<UserProduct, Long> {
	/**
	 * Method to search user by email id
	 * 
	 * @param emailId
	 * @return User entity with user's details if email id is valid
	 */
	@Transactional
	UserProduct findByUserIdAndProductId(Long userId, Long productId);
}
