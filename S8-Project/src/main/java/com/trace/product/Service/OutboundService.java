package com.trace.product.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trace.product.Model.Outbound;
import com.trace.product.Repository.OutboundRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboundService {
    
    private final OutboundRepository outboundRepository;

    public Outbound saveOutbound(Outbound transaction) {
        return outboundRepository.save(transaction);
    }

    public List<Outbound> getOutboundTransactions() {
        return outboundRepository.findAll();
    }

    public Outbound updateOutbound(String id, Outbound transaction) {
        Outbound trans = outboundRepository.findById(id).get();
        if(trans != null) {
            return outboundRepository.save(transaction);
        }
        return null;
    }

    public Outbound deleteOutbound(String id) {
        Outbound trans = outboundRepository.findById(id).get();
        if(trans != null){
            outboundRepository.delete(trans);
            return trans;
        }
        return null;
    }
}
