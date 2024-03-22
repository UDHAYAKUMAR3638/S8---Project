package com.trace.product.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.trace.product.Dto.UpdateProduction;
import com.trace.product.Model.Composition;
import com.trace.product.Model.Inbound;
import com.trace.product.Model.InputLot;
import com.trace.product.Model.Production;
import com.trace.product.Repository.InboundRepository;
import com.trace.product.Repository.ProductionRepository;



@Service
public class ProductionService {
    @Autowired
    ProductionRepository productionRepository;

    @Autowired
    InboundRepository inboundRepository;

    @Autowired
    MongoTemplate template;

    public Production save(Production production){
        production.setRemainingQuantity(production.getTotalQuantity());
        HashMap<String,Double> map = new HashMap<>();
        Double total = 0.0;
        ArrayList<InputLot> lots = production.getInputLots();
        for(InputLot lot:lots){
            Inbound inbound = inboundRepository.findByLotNo(lot.getLotNo());
            for(Composition composition : inbound.getMaterialComposition()){
            if(map.containsKey(composition.getMaterialType())){
                map.put(composition.getMaterialType(), map.get(composition.getMaterialType())+composition.getPercentage());
            }
            else{
                map.put(composition.getMaterialType(),composition.getPercentage());
            }
            total += composition.getPercentage();
        }
        }
        final Double correctTotal = total;
        ArrayList<Composition> compositions = new ArrayList<>();
        map.forEach((key,value) -> {
            Composition composition = new Composition();
            composition.setMaterialType(key);
            Double percentage = (value/correctTotal) *100;
            composition.setPercentage(percentage);
            compositions.add(composition);
        });
        production.setMaterialComposition(compositions);
        return productionRepository.save(production);
    }

    public Production update(UpdateProduction update){
        Production production = productionRepository.findById(update.getId()).get();
        if((production.getRemainingQuantity()-update.getQuantity() > 0))
        production.setRemainingQuantity(production.getRemainingQuantity()-update.getQuantity());
        return productionRepository.save(production);
    }

    public List<Production> getProduction(String lotNo, String materialType){
        Criteria criteria = Criteria.where("lotNo").regex(lotNo).andOperator(Criteria.where("materialName").regex(materialType));
        Query query = new Query(criteria);
        return template.find(query, Production.class);
    }
}
