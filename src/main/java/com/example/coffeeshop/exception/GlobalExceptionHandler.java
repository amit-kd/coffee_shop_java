package com.example.coffeeshop.exception;

import static com.example.coffeeshop.utils.CoffeeShopConstant.DATA_NOT_FOUND;
import static com.example.coffeeshop.utils.CoffeeShopConstant.DATA_NOT_FOUND_MESSAGE;
import static com.example.coffeeshop.utils.CoffeeShopConstant.DB_ERROR_CODE;
import static com.example.coffeeshop.utils.CoffeeShopConstant.DB_RETRIEVAL_ERROR_MESSAGE;
import static com.example.coffeeshop.utils.CoffeeShopConstant.INTERNAL_ERROR_CODE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.coffeeshop.model.CustomError;
import com.example.coffeeshop.model.CustomResponse;
import com.example.coffeeshop.utils.ResponseBuilder;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ResponseBuilder<Object> responseBuilder;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({ SQLException.class, DataIntegrityViolationException.class })
	public ResponseEntity<CustomResponse<Object>> handleSQLException(Exception ex) {

		this.logger.error("Error Track is:::-------");
		ex.printStackTrace();
		CustomError error = new CustomError();
		error.setCode(DB_ERROR_CODE).setMessage(DB_RETRIEVAL_ERROR_MESSAGE)
				.setType(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());

		List<CustomError> errors = new ArrayList<>();
		errors.add(error);
		this.logger.error("There was an Error trying to read/write to the database:: SQL Exception");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(this.responseBuilder.buildResponse(Collections.emptyList(), errors));
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class,
			NullPointerException.class })
	public ResponseEntity<CustomResponse<Object>> handleEmptyResultException(Exception ex) {

		this.logger.error("Error Track is:::-------");
		ex.printStackTrace();
		CustomError error = new CustomError();
		error.setCode(DATA_NOT_FOUND).setMessage(DATA_NOT_FOUND_MESSAGE)
				.setType(HttpStatus.NOT_FOUND.getReasonPhrase());
		List<CustomError> errors = new ArrayList<>();

		errors.add(error);
		this.logger.error("There was an Error trying to retrieve the data");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(this.responseBuilder.buildResponse(Collections.emptyList(), errors));
	}

	@ExceptionHandler({ CoffeeShopBaseException.class })
	public ResponseEntity<CustomResponse<Object>> handleCustomException(CoffeeShopBaseException ex) {

		this.logger.error("Error Track is:::-------");
		ex.printStackTrace();
		this.logger.error("There was an Error trying to retrieve the data");
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.responseBuilder.buildResponse(Collections.emptyList(), ex.getErrors()));
	}

	@ExceptionHandler({ Exception.class, RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<CustomResponse<Object>> handleCustomException(Exception ex) {

		this.logger.error("Error Track is:::-------");
		ex.printStackTrace();
		CustomError error = new CustomError();
		error.setCode(INTERNAL_ERROR_CODE).setMessage(ex.getMessage())
				.setType(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		List<CustomError> errors = new ArrayList<>();
		errors.add(error);
		this.logger.error(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(this.responseBuilder.buildResponse(Collections.emptyList(), errors));
	}
}
