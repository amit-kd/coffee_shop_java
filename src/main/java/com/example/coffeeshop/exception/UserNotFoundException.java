package com.example.coffeeshop.exception;

public class UserNotFoundException extends CoffeeShopBaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserNotFoundException() {
		super();
	}

	UserNotFoundException(String s) {
		super(s);
	}
}
