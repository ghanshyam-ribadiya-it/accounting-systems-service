package com.accounting.expensify;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpensePolicyRequest {
	private String type;
	private Credentials credentials;
	private InputSettings inputSettings;
}
