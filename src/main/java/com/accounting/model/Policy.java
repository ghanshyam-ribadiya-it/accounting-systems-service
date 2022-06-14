package com.accounting.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "policy")
public class Policy {
	private String id;
	private String name;
	private String type;
	private String role;
	private String outputCurrency;
}
