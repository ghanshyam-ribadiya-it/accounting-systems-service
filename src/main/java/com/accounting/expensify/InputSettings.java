package com.accounting.expensify;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputSettings {
	private String type;
	private Boolean adminOnly;
	private String userEmail;
}
