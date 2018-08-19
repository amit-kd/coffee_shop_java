package com.example.coffeeshop.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.coffeeshop.model.CustomError;
import com.example.coffeeshop.model.CustomResponse;

@Component
public class ResponseBuilder<T> {

	public ResponseBuilder() {
	}

	public CustomResponse<T> buildResponse(List<T> payload, List<CustomError> errors) {

		CustomResponse<T> response = new CustomResponse<>();
		response.setErrors(errors).setPayload(payload).setTimeStamp(LocalDateTime.now().toString());

		return response;
	}

}
