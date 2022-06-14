package com.accounting.expensify;

import java.util.List;

import lombok.Data;

@Data
public class ExpensePolicyResponse {
	private List<PolicyDetail> policyList;
	private String responseCode;
}
