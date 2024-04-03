package com.trace.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trace.product.Dto.UpdateProduction;
import com.trace.product.Model.Production;
import com.trace.product.Service.ProductionService;


@RestController
@RequestMapping("production")
@CrossOrigin
public class ProductionController {

    @Autowired
    ProductionService productionService;
    
    @PostMapping
    public ResponseEntity<Production> save(@RequestBody Production production) {
        return ResponseEntity.ok(productionService.save(production));
    }

    @PutMapping
    public ResponseEntity<Production> update(@RequestBody UpdateProduction updateProduction) {
        return ResponseEntity.ok(productionService.update(updateProduction));
    }

    @GetMapping
    public ResponseEntity<List<Production>> get(@RequestParam String lotNo, @RequestParam String material) {
        return ResponseEntity.ok(productionService.getProduction(lotNo,material));
    }
}
