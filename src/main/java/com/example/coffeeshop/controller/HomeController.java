package com.example.coffeeshop.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.dto.UserRegisterRequest;
import com.example.coffeeshop.dto.UserRegisterResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.model.CustomResponse;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.utils.ResponseBuilder;

@RestController
@CrossOrigin
@RequestMapping(path = "/coffeeshop/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseBuilder<UserRegisterResponse> responseBuilder;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse<UserRegisterResponse>> registerUser(
			@RequestBody UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException {
		logger.info("Entered in method-sendEmail of class-EmailController at {}", System.currentTimeMillis());
		logger.debug("input payload: {}", userRegisterRequest);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections.singletonList(this.userService.registerUser(userRegisterRequest)),
						Collections.emptyList()));
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public ResponseEntity<CustomResponse<UserRegisterResponse>> loginUser(
			@RequestBody UserRegisterRequest userRegisterRequest) throws CoffeeShopBaseException {
		logger.info("Entered in method-sendEmail of class-EmailController at {}", System.currentTimeMillis());
		logger.debug("input payload: {}", userRegisterRequest);
		return ResponseEntity.ok().body(responseBuilder.buildResponse(
				Collections.singletonList(this.userService.loginUser(userRegisterRequest)), Collections.emptyList()));
	}
}
