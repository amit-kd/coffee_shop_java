package com.example.coffeeshop.dto;

import org.springframework.stereotype.Component;

@Component
public class UserRegisterResponse {
	private Boolean success = false;
	
	private String id;
	private String name;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
