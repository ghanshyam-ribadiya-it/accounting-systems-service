package com.accounting.exception;

import com.accounting.dto.APIResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private APIResponse apiResponse;

	public SystemException() {
		super();
	}

	public SystemException(APIResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}
}
