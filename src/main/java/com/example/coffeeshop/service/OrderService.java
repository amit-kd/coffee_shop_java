package com.example.coffeeshop.service;

import java.text.ParseException;
import java.util.List;

import com.example.coffeeshop.dto.OrderResponse;
import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.exception.CoffeeShopBaseException;

public interface OrderService {
	public List<OrderResponse> retriveOrders(String userId) throws CoffeeShopBaseException;

	public OrderResponse retriveOrderDetails(String userId, String orderId) throws CoffeeShopBaseException;
	
	public OrderResponse updateOrderDetails(String userId,OrderResponse orderResponse) throws CoffeeShopBaseException, ParseException;
	
	public OrderResponse postOrderDetails(String userId,OrderResponse orderResponse) throws CoffeeShopBaseException, ParseException;
}
