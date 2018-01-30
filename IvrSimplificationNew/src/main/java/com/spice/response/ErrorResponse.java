package com.spice.response;

import lombok.Data;

@Data
public class ErrorResponse {
	private String status,responseId,errorDescription,response;;
	public ErrorResponse (String message) {
		this.errorDescription = message;
	}
}
