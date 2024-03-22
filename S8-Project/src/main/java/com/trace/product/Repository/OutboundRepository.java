package com.trace.product.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trace.product.Model.Outbound;

@Repository
public interface OutboundRepository extends MongoRepository<Outbound,String>{
    
}
