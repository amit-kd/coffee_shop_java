package com.example.coffeeshop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coffeeshop.dto.ProductResponse;
import com.example.coffeeshop.entity.Product;
import com.example.coffeeshop.entity.UserProduct;
import com.example.coffeeshop.exception.CoffeeShopBaseException;
import com.example.coffeeshop.repository.ProductRepo;
import com.example.coffeeshop.repository.UserProductRepo;
import com.example.coffeeshop.repository.UserRepo;
import com.example.coffeeshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserProductRepo userProductRepo;

	@Override
	public List<ProductResponse> retriveProducts() throws CoffeeShopBaseException {
		List<ProductResponse> productResponses = new ArrayList<>();
		List<Product> products = productRepo.findAll();
		products.forEach(prod -> productResponses.add(this.mapProductToDTO(prod)));
		return productResponses;
	}

	@Override
	public ProductResponse retriveProductDetails(String userId, String productId) throws CoffeeShopBaseException {
		Product product = productRepo.findById(Long.valueOf(productId));
		if (product == null) {
			throw new CoffeeShopBaseException();
		}	
		ProductResponse productResponse = this.mapProductToDTO(product);
		if(userId.matches("-?\\d+")){
			UserProduct userProduct = userProductRepo.findByUserIdAndProductId(Long.valueOf(userId), Long.valueOf(productId));
			if (userProduct != null) {
				productResponse.setIsLiked(true);
			}
		}
		return productResponse;
	}

	@Override
	public ProductResponse likeProduct(String userId, String productId) throws CoffeeShopBaseException {
		Product product = productRepo.findById(Long.valueOf(productId));
		if (product == null) {
			throw new CoffeeShopBaseException();
		}	
		ProductResponse productResponse = this.mapProductToDTO(product);
		if(userId.matches("-?\\d+")){
			UserProduct userProduct = userProductRepo.findByUserIdAndProductId(Long.valueOf(userId), Long.valueOf(productId));
			if (userProduct != null) {
				userProductRepo.delete(userProduct);
				productResponse.setIsLiked(false);
			}else{
				userProduct = new UserProduct();
				userProduct.setUserId(Long.valueOf(userId));
				userProduct.setProductId(Long.valueOf(productId));
				userProductRepo.save(userProduct);
				productResponse.setIsLiked(true);
			}
		}
		return productResponse;
	}

	
	private ProductResponse mapProductToDTO(Product product) {
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
		return productResponse;
	}

}
