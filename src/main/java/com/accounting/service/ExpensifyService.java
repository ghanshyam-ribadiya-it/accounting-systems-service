package com.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.accounting.config.ApplicationProps;
import com.accounting.dto.APIResponse;
import com.accounting.exception.SystemException;
import com.accounting.expensify.Credentials;
import com.accounting.expensify.ExpensePolicyRequest;
import com.accounting.expensify.ExpensePolicyResponse;
import com.accounting.expensify.InputSettings;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpensifyService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ApplicationProps applicationProps;

	public ExpensePolicyResponse callExpensifyPolicy() {
		try {
			ExpensePolicyRequest expensePolicyRequest = prepareExpensePolicyRequest();
			ObjectMapper mapper = new ObjectMapper();
			String jsonObject = mapper.writeValueAsString(expensePolicyRequest);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("requestJobDescription", jsonObject);
	
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
	
			ResponseEntity<String> response = restTemplate.exchange(applicationProps.getBaseUrl(), HttpMethod.POST, entity, String.class);
			log.info("response : {}", response.getBody());
			
			ExpensePolicyResponse expensePolicyResponse = mapper.readValue(response.getBody(), ExpensePolicyResponse.class);
			log.info("response code: {} and size : {}", expensePolicyResponse.getResponseCode(), expensePolicyResponse.getPolicyList().size());
			
			return expensePolicyResponse;
		} 
		catch (ResourceAccessException e) {
			log.error("ResourceAccessException in call ExpensifyPolicy service api , response : ", e);
			throw new SystemException(new APIResponse("ResourceAccessException while call external ExpensifyPolicy service", "408"));
		}
		catch(HttpStatusCodeException e) {
			log.error("HttpStatusCodeException in call ExpensifyPolicy service api , response : ", e);
			throw new SystemException(new APIResponse("HttpStatusCodeException while call external ExpensifyPolicy service", "424"));
		}
		catch (Exception e) {
			log.error("Exception in call callExpensifyPolicy service api , response : ", e);
			throw new SystemException(new APIResponse("Exception while call external ExpensifyPolicy service", "424"));
		}
	}
	
	private ExpensePolicyRequest prepareExpensePolicyRequest() {
		ExpensePolicyRequest expenseRequest = new ExpensePolicyRequest();
		expenseRequest.setType("get");
		
		Credentials credentials = new Credentials();
		credentials.setPartnerUserID(applicationProps.getUserID());
		credentials.setPartnerUserSecret(applicationProps.getUserSecret());
		expenseRequest.setCredentials(credentials);
		
		InputSettings inputSettings = new InputSettings();
		inputSettings.setType("policyList");
		inputSettings.setAdminOnly(true);
		inputSettings.setUserEmail(applicationProps.getUserEmail());
		expenseRequest.setInputSettings(inputSettings);
		
		return expenseRequest;
	}

}
