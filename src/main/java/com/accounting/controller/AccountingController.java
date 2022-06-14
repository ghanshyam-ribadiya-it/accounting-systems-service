package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.dto.PolicyResponse;
import com.accounting.service.PolicyService;

@RestController
public class AccountingController {

	@Autowired
	private PolicyService policyService;

	@GetMapping("/policy")
	public ResponseEntity<PolicyResponse> getPolicyDetails() {
		PolicyResponse policyResponse = policyService.getPolicyDetails();
		return new ResponseEntity<PolicyResponse>(policyResponse, HttpStatus.OK);
	}
}
