package com.example.coffeeshop.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1763636432440874549L;
	private String id;
	private String title;
	private List<String> privewImages;
	private String rating;
	private String noOfReviews;
	private String description;
	private String currentPrice;
	private String enjoyedPercentage;
	private String noOfVotes;
	private Boolean isLiked = false;
	private Integer quantity = 1;
	private Integer stockCount;
	private Integer thresholdStockCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPrivewImages() {
		return privewImages;
	}

	public void setPrivewImages(List<String> privewImages) {
		this.privewImages = privewImages;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(String noOfReviews) {
		this.noOfReviews = noOfReviews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getEnjoyedPercentage() {
		return enjoyedPercentage;
	}

	public void setEnjoyedPercentage(String enjoyedPercentage) {
		this.enjoyedPercentage = enjoyedPercentage;
	}

	public String getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(String noOfVotes) {
		this.noOfVotes = noOfVotes;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getThresholdStockCount() {
		return thresholdStockCount;
	}

	public void setThresholdStockCount(Integer thresholdStockCount) {
		this.thresholdStockCount = thresholdStockCount;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
