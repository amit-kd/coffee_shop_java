package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.UserRegisterRequest;
import com.example.coffeeshop.dto.UserRegisterResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;

public interface UserService {
	public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException;

	public UserRegisterResponse loginUser(UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException;
}
