package com.accounting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "expensify-service")
public class ApplicationProps {
	private String baseUrl;
	private String userID;
	private String userSecret;
	private String userEmail;
}
