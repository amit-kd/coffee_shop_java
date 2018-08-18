package com.example.coffeeshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 4983016347878900738L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "privew_images", nullable = false)
	private String privewImages;

	@Column(name = "rating", nullable = false)
	private String rating;

	@Column(name = "reviews_count", nullable = false)
	private Integer noOfReviews;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "original_price", nullable = false)
	private Double originalPrice;

	@Column(name = "current_price", nullable = false)
	private Double currentPrice;

	@Column(name = "enjoyed", nullable = false)
	private Double enjoyedPercentage;

	@Column(name = "votes_count", nullable = false)
	private Integer noOfVotes;

	@Column(name = "stock_count", nullable = false)
	private Integer stockCount;

	@Column(name = "threshold_stock_count", nullable = false)
	private Integer thresholdStockCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrivewImages() {
		return privewImages;
	}

	public void setPrivewImages(String privewImages) {
		this.privewImages = privewImages;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(Integer noOfReviews) {
		this.noOfReviews = noOfReviews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Double getEnjoyedPercentage() {
		return enjoyedPercentage;
	}

	public void setEnjoyedPercentage(Double enjoyedPercentage) {
		this.enjoyedPercentage = enjoyedPercentage;
	}

	public Integer getNoOfVotes() {
		return noOfVotes;
	}

	public void setNoOfVotes(Integer noOfVotes) {
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

}
