package com.example.coffeeshop.exception;

import java.util.ArrayList;
import java.util.List;

import com.example.coffeeshop.model.CustomError;

public class CoffeeShopBaseException extends Exception {

	List<CustomError> errors;

	private static final long serialVersionUID = 1L;

	public CoffeeShopBaseException() {
		super();
		errors = new ArrayList<>();
	}

	public CoffeeShopBaseException(List<CustomError> errors) {
		super();
		this.errors = errors;
	}

	public CoffeeShopBaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CoffeeShopBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoffeeShopBaseException(String message) {
		super(message);
	}

	public CoffeeShopBaseException(Throwable cause) {
		super(cause);
	}

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}
}
