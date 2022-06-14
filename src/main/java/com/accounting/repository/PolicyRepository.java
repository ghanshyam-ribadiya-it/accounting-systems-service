package com.accounting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accounting.model.Policy;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, Integer> {

}
