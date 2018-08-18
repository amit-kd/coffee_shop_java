package com.example.coffeeshop.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.coffeeshop.dto.UserRegisterRequest;
import com.example.coffeeshop.dto.UserRegisterResponse;
import com.example.coffeeshop.entity.Role;
import com.example.coffeeshop.entity.User;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.repository.RoleRepo;
import com.example.coffeeshop.repository.UserRepo;
import com.example.coffeeshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final String userRole = "STANDARD_USER";
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException {
		UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
		User user = userRepo.findByEmail(userRegisterRequest.getEmail());
		if (user != null) {
			throw new CoffeeShopBaseException();
		}
		user = new User();
		user.setEmail(userRegisterRequest.getEmail());
		user.setName(userRegisterRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()));
		user.setDob(userRegisterRequest.getDob());
		Role role = roleRepo.findByRoleName(userRole);
		user.setRoles(Collections.singletonList(role));
		userRepo.save(user);
		userRegisterResponse.setSuccess(true);
		return userRegisterResponse;
	}

	@Override
	public UserRegisterResponse loginUser(UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException {
		UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
		User user = userRepo.findByEmail(userRegisterRequest.getEmail());
		if (user == null) {
			throw new CoffeeShopBaseException();
		}
		userRegisterResponse.setSuccess(true);
		userRegisterResponse.setId(user.getId().toString());
		userRegisterResponse.setName(user.getName());
		return userRegisterResponse;
	}

}
