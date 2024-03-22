package com.trace.product.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trace.product.Model.Outbound;
import com.trace.product.Service.OutboundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("outbound")
public class OutboundController {
    private final OutboundService OutboundService;

    @PostMapping("save")
    public ResponseEntity<Outbound> saveOutbound(@RequestBody Outbound OutboundTrans) {
        return ResponseEntity.ok(OutboundService.saveOutbound(OutboundTrans));
    }

    @GetMapping("get")
    public ResponseEntity<List<Outbound>> getOutboundTransactions() {
        return ResponseEntity.ok(OutboundService.getOutboundTransactions());
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Outbound> updateOutbound(@PathVariable String id, @RequestBody Outbound OutboundTrans) {
        return ResponseEntity.ok(OutboundService.updateOutbound(id, OutboundTrans));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Outbound> deleteOutbound(@PathVariable String id) {
        return ResponseEntity.ok(OutboundService.deleteOutbound(id));
    }
}
