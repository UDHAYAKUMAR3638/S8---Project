package com.trace.product.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trace.product.Model.Production;

public interface ProductionRepository extends MongoRepository<Production, String>{
    
}
