package com.example.coffeeshop.service.impl;

import static org.mockito.Matchers.longThat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coffeeshop.dto.OrderResponse;
import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.entity.Order;
import com.example.coffeeshop.entity.OrderProduct;
import com.example.coffeeshop.entity.Product;
import com.example.coffeeshop.entity.User;
import com.example.coffeeshop.entity.UserOrder;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.repository.OrderProductRepo;
import com.example.coffeeshop.repository.OrderRepo;
import com.example.coffeeshop.repository.UserOrderRepo;
import com.example.coffeeshop.repository.UserRepo;
import com.example.coffeeshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepo orderRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserOrderRepo userOrderRepo;
	
	@Autowired
	OrderProductRepo orderProductRepo;

	@Override
	public List<OrderResponse> retriveOrders(String userId) throws CoffeeShopBaseException {
		List<OrderResponse> orderResponses = new ArrayList<>();
		User user = userRepo.findById(Long.valueOf(userId));
		if (user == null) {
			throw new CoffeeShopBaseException();
		}
		user.getOrders().forEach(order -> orderResponses.add(this.mapOrderToDTO(order)));
		return orderResponses;
	}

	@Override
	public OrderResponse retriveOrderDetails(String userId, String orderId) throws CoffeeShopBaseException {
		UserOrder userOrder = userOrderRepo.findByUserIdAndOrderId(Long.valueOf(userId), Long.valueOf(orderId));
		if (userOrder == null) {
			throw new CoffeeShopBaseException();
		}
		Order order = orderRepo.findById(Long.valueOf(orderId));
		if (order == null) {
			throw new CoffeeShopBaseException();
		}
		return this.mapOrderToDTO(order);
	}

	@Override
	public OrderResponse updateOrderDetails(String userId, OrderResponse orderResponse)
			throws CoffeeShopBaseException, ParseException {
		UserOrder userOrder = userOrderRepo.findByUserIdAndOrderId(Long.valueOf(userId),
				Long.valueOf(orderResponse.getId()));
		if (userOrder == null) {
			throw new CoffeeShopBaseException();
		}
		Order order = orderRepo.findById(Long.valueOf(orderResponse.getId()));
		if (order == null) {
			throw new CoffeeShopBaseException();
		}
		order = orderRepo.save(this.mapDTOToOrder(order, orderResponse));
		Long orderId = order.getId();
		orderResponse.getProducts().forEach(prod -> {
			OrderProduct orderProduct = orderProductRepo.findByOrderIdAndProductId(orderId, Long.valueOf(prod.getId()));	
			if(orderProduct != null){
				orderProduct.setQuantity(prod.getQuantity());
			}
			orderProductRepo.save(orderProduct);
			
		});
		
		return orderResponse;
	}

	@Override
	public OrderResponse postOrderDetails(String userId, OrderResponse orderResponse)
			throws CoffeeShopBaseException, ParseException {
		Order order = orderRepo.save(this.mapDTOToOrder(new Order(), orderResponse));
		UserOrder userOrder = new UserOrder();
		userOrder.setUserId(Long.valueOf(userId));
		userOrder.setOrderId(order.getId());
		userOrderRepo.save(userOrder);
		Long orderId = order.getId();
		orderResponse.getProducts().forEach(prod -> {
			OrderProduct orderProduct = orderProductRepo.findByOrderIdAndProductId(orderId, Long.valueOf(prod.getId()));	
			if(orderProduct != null){
				orderProduct.setQuantity(prod.getQuantity());
			}
			orderProductRepo.save(orderProduct);			
		});		
		return orderResponse;
	}

	private Order mapDTOToOrder(Order order, OrderResponse dto) throws ParseException {
		List<Product> products = new ArrayList<>();
		order.setOrderDateTime(new Timestamp(
				new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dto.getDate() + " " + dto.getTime()).getTime()));
		dto.getProducts().forEach(prod -> {
			OrderProduct orderProduct = orderProductRepo.findByOrderIdAndProductId(order.getId(), Long.valueOf(prod.getId())
					);
			products.add(this.mapDTOToProduct(prod));
		});
		order.setProducts(products);
		if (dto.getIsSubmit() != null && dto.getIsSubmit()) {
			order.setStatus("CONFIRMING");
		} else {
			order.setStatus("SAVED");
		}

		order.setTotal(dto.getTotal());
		return order;
	}

	private Product mapDTOToProduct(ProductResponse productResponse) {
		Product product = new Product();

		product.setCurrentPrice(Double.valueOf(productResponse.getCurrentPrice()));
		product.setDescription(productResponse.getDescription());
		product.setEnjoyedPercentage(Double.valueOf(productResponse.getEnjoyedPercentage()));
		product.setId(Long.valueOf(productResponse.getId().toString()));
		product.setNoOfReviews(Integer.valueOf(productResponse.getNoOfReviews().toString()));
		product.setNoOfVotes(Integer.valueOf(productResponse.getNoOfVotes().toString()));
		product.setRating(productResponse.getRating());
		product.setStockCount(productResponse.getStockCount());
		product.setThresholdStockCount(productResponse.getThresholdStockCount());
		product.setTitle(productResponse.getTitle());
		product.setPrivewImages(String.join(", ", productResponse.getPrivewImages()));
		return product;

	}

	private OrderResponse mapOrderToDTO(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		List<ProductResponse> productResponses = new ArrayList<>();
		String datePattern = "yyyy-MM-dd";
		String timePattern = "HH:mm";
		String date = new SimpleDateFormat(datePattern).format(new Date(order.getOrderDateTime().getTime()));
		String time = new SimpleDateFormat(timePattern).format(new Date(order.getOrderDateTime().getTime()));
		orderResponse.setDate(date);
		orderResponse.setTime(time);
		orderResponse.setId(order.getId().toString());
		order.getProducts().forEach(prod -> productResponses.add(this.mapProductToDTO(order.getId(),prod)));
		orderResponse.setProducts(productResponses);
		orderResponse.setStatus(order.getStatus());
		orderResponse.setTotal(order.getTotal());
		return orderResponse;
	}

	private ProductResponse mapProductToDTO(Long orderId, Product product) {		
		ProductResponse productResponse = new ProductResponse();
		productResponse.setCurrentPrice(product.getCurrentPrice().toString());
		productResponse.setDescription(product.getDescription());
		productResponse.setEnjoyedPercentage(product.getEnjoyedPercentage().toString());
		productResponse.setId(product.getId().toString());
		productResponse.setNoOfReviews(product.getNoOfReviews().toString());
		productResponse.setNoOfVotes(product.getNoOfVotes().toString());
		productResponse.setRating(product.getRating().toString());
		productResponse.setStockCount(product.getStockCount());
		productResponse.setThresholdStockCount(product.getThresholdStockCount());
		productResponse.setTitle(product.getTitle());
		productResponse.setPrivewImages(Arrays.asList(product.getPrivewImages().split(",")));
		OrderProduct orderProduct = orderProductRepo.findByOrderIdAndProductId(orderId, product.getId());
		if(orderProduct != null){
			if(orderProduct.getQuantity() != null && orderProduct.getQuantity() >=1){
				productResponse.setQuantity(orderProduct.getQuantity());	
			}
		}
		return productResponse;
	}

}
