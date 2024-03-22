package com.trace.product.Controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trace.product.Dto.CompositionDto;
import com.trace.product.Dto.InboundDto;
import com.trace.product.Model.Inbound;
import com.trace.product.Service.InboundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("inbound/")
@CrossOrigin
@RequiredArgsConstructor
public class InboundController {
    private final InboundService inboundService;

    @PostMapping("save")
    public ResponseEntity<Inbound> saveInbound(@ModelAttribute InboundDto inboundTrans, @RequestParam("file") MultipartFile[] image) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<CompositionDto> compositionDtoList = objectMapper.readValue(inboundTrans.getMaterialComposition(), new TypeReference<List<CompositionDto>>() {});
            return ResponseEntity.ok(inboundService.saveInbound(inboundService.convertInbound(inboundTrans,compositionDtoList, image)));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    return new ResponseEntity<>(new Inbound(), HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<List<Inbound>> getInboundTransactions() {
        return ResponseEntity.ok(inboundService.getInboundTransactions());
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Inbound> updateInbound(@PathVariable String id, @RequestBody Inbound inboundTrans) {
        return ResponseEntity.ok(inboundService.updateInbound(id, inboundTrans));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Inbound> deleteInbound(@PathVariable String id) {
        return ResponseEntity.ok(inboundService.deleteInbound(id));
    }
}
