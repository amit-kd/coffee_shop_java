package com.example.coffeeshop.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.model.CustomResponse;
import com.example.coffeeshop.service.ProductService;
import com.example.coffeeshop.utils.ResponseBuilder;

@RestController
@RequestMapping(path = "/coffeeshop/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ResponseBuilder<ProductResponse> responseBuilder;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<ProductResponse>> retriveProducts() throws CoffeeShopBaseException {
		logger.info("Entered in method-retriveProducts of class-ProductController at {}", System.currentTimeMillis());
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(this.productService.retriveProducts(), Collections.emptyList()));
	}

	@RequestMapping(value = "/products/{userId}/{productId}", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<ProductResponse>> retriveProductDetails(@PathVariable String userId,
			@PathVariable String productId) throws CoffeeShopBaseException {
		logger.info("Entered in method-retriveProductDetails of class-ProductController at {}",
				System.currentTimeMillis());
		logger.debug("input payload: {}, {}", userId, productId);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections.singletonList(this.productService.retriveProductDetails(userId, productId)),
						Collections.emptyList()));
	}
	
	@RequestMapping(value = "/products/{userId}/{productId}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse<ProductResponse>> likeProduct(@PathVariable String userId,
			@PathVariable String productId) throws CoffeeShopBaseException {
		logger.info("Entered in method-likeProduct of class-ProductController at {}",
				System.currentTimeMillis());
		logger.debug("input payload: {}, {}", userId, productId);
		return ResponseEntity.ok()
				.body(responseBuilder.buildResponse(
						Collections.singletonList(this.productService.likeProduct(userId, productId)),
						Collections.emptyList()));
	}
}
