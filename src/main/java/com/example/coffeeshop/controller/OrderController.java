package com.example.coffeeshop.controller;

import java.text.ParseException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.dto.OrderResponse;
import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.model.CustomResponse;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.utils.ResponseBuilder;

@RestController
@RequestMapping(path = "/coffeeshop/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private ResponseBuilder<OrderResponse> responseBuilder;

	@RequestMapping(value = "/user/orders/{userId}", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<OrderResponse>> retriveOrders(@PathVariable String userId)
			throws CoffeeShopBaseException {
		logger.info("Entered in method-retriveOrders of class-OrderController at {}", System.currentTimeMillis());
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(this.orderService.retriveOrders(userId), Collections.emptyList()));
	}

	@RequestMapping(value = "/user/orders/{userId}/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<OrderResponse>> retriveOrderDetails(@PathVariable String userId,
			@PathVariable String orderId) throws CoffeeShopBaseException {
		logger.info("Entered in method-retriveOrderDetails of class-OrderController at {}", System.currentTimeMillis());
		logger.debug("input payload: {}, {}", userId, orderId);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections.singletonList(this.orderService.retriveOrderDetails(userId, orderId)),
						Collections.emptyList()));
	}

	@RequestMapping(value = "/user/order/{userId}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse<OrderResponse>> updateOrderDetails(@PathVariable String userId,
			@RequestBody OrderResponse orderResponse)
			throws CoffeeShopBaseException, ParseException {
		logger.info("Entered in method-postOrderDetails of class-OrderController at {}", System.currentTimeMillis());
		logger.debug("input payload: {}, {}", userId, orderResponse);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections
								.singletonList(this.orderService.updateOrderDetails(userId, orderResponse)),
						Collections.emptyList()));
	}

	@RequestMapping(value = "/user/orders/{userId}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse<OrderResponse>> postOrderDetails(@PathVariable String userId,
			@RequestBody OrderResponse orderResponse)
			throws CoffeeShopBaseException, ParseException {
		logger.info("Entered in method-postOrderDetails of class-OrderController at {}", System.currentTimeMillis());
		logger.debug("input payload: {}, {}", userId, orderResponse);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections.singletonList(this.orderService.postOrderDetails(userId, orderResponse)),
						Collections.emptyList()));
	}
}
