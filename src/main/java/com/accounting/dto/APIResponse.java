package com.accounting.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {
	private String responseMessage;
	private String responseCode;

	public APIResponse(String responseMessage, String responseCode) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}
}
