package com.accounting.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyResponse extends APIResponse {

	public PolicyResponse(String responseMessage, String responseCode) {
		super(responseMessage, responseCode);
	}

}