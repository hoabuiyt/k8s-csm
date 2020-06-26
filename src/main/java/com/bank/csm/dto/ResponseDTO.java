package com.bank.csm.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private T data;
	
	private String message;
	
	private HttpStatus status;
	
	private Date responseTime;
	
	public ResponseDTO(T data, String message, HttpStatus status) {
		super();
		this.data = data;
		this.message = message;
		this.status = status;
		responseTime = new Date();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	
}
