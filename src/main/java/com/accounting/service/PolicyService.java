package com.accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.accounting.dto.PolicyResponse;
import com.accounting.expensify.ExpensePolicyResponse;
import com.accounting.expensify.PolicyDetail;
import com.accounting.model.Policy;
import com.accounting.repository.PolicyRepository;

@Service
public class PolicyService {
	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private ExpensifyService expensifyService;

	public PolicyResponse getPolicyDetails() {
		ExpensePolicyResponse expensePolicyResponse = expensifyService.callExpensifyPolicy();

		if (expensePolicyResponse != null && !CollectionUtils.isEmpty(expensePolicyResponse.getPolicyList())) {
			expensePolicyResponse.getPolicyList().forEach(policyDetail -> {
				Policy policy = preparePolicy(policyDetail);
				policyRepository.save(policy);
			});
			
			return new PolicyResponse("Policy details has been loaded successfully", "200");
		}

		return new PolicyResponse("No Policy Details are found", "200");
	}
	
	private Policy preparePolicy(PolicyDetail policyDetail) {
		Policy policy = new Policy();
		policy.setId(policyDetail.getId());
		policy.setName(policyDetail.getName());
		policy.setOutputCurrency(policyDetail.getOutputCurrency());
		policy.setRole(policyDetail.getRole());
		policy.setType(policyDetail.getType());
		return policy;
	}
}
