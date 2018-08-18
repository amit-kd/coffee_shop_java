package com.example.coffeeshop.service;

import java.util.List;

import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;

public interface ProductService {
	public List<ProductResponse> retriveProducts() throws CoffeeShopBaseException;

	public ProductResponse retriveProductDetails(String userId, String productId) throws CoffeeShopBaseException;
	
	public ProductResponse likeProduct(String userId, String productId) throws CoffeeShopBaseException;
}
