package com.trace.product.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trace.product.Dto.CompositionDto;
import com.trace.product.Dto.InboundDto;
import com.trace.product.Model.Composition;
import com.trace.product.Model.Inbound;
import com.trace.product.Repository.InboundRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InboundService {
    
    private final InboundRepository inboundRepository;

    public Inbound saveInbound(Inbound transaction) {
        return inboundRepository.save(transaction);
    }

    public List<Inbound> getInboundTransactions() {
        return inboundRepository.findAll();
    }

    public Inbound updateInbound(String id, Inbound transaction) {
        Inbound trans = inboundRepository.findById(id).get();
        if(trans != null) {
            return inboundRepository.save(transaction);
        }
        return null;
    }

    public Inbound deleteInbound(String id) {
        Inbound trans = inboundRepository.findById(id).get();
        if(trans != null){
            inboundRepository.delete(trans);
            return trans;
        }
        return null;
    }

    public Inbound convertInbound(InboundDto newInbound, List<CompositionDto> compositionDtos, MultipartFile[] images) {
        
        List<CompositionDto> listCompositions = new ArrayList<>();
        for(int i=0;i< compositionDtos.size();i++) {
            listCompositions.addAll(compositionDtos);
        }
        Inbound inbound = new Inbound();
        inbound.setLotNo(newInbound.getLotNo());

        List<Composition> list2 = new ArrayList<>();
        for(int i=0;i<listCompositions.size();i++) {
            Composition composition = new Composition();
            composition.setMaterialType(listCompositions.get(i).getMaterialType());
            composition.setPercentage(Double.parseDouble(listCompositions.get(i).getPercentage()));
            list2.addLast(composition);
        }
        
        inbound.getMaterialComposition().addAll(list2);
        inbound.setInvoiceNo(Integer.parseInt(newInbound.getInvoiceNo()));
        inbound.setSellerName(newInbound.getSellerName());
        inbound.setInvoiceDate(newInbound.getInvoiceDate());
        inbound.setAddedBy(newInbound.getAddedBy());
        inbound.setTotalQuantity(Double.parseDouble(newInbound.getTotalQuantity()));
        inbound.setRemainingQuantity(Double.parseDouble(newInbound.getRemainingQuantity()));

        for(int i=0;i<images.length;i++ ){
            inbound.getImages().addLast(imageConvet(images[i]));
        }

        return inbound;
    }

    public String imageConvet(MultipartFile file) {
        String url = "";
        String contentType = file.getContentType();

        if (contentType != null && contentType.startsWith("image")) {
            url = "http://localhost:8080/static/images/" + file.getOriginalFilename();
            try {
                file.transferTo(new File("C:/Users/ARULMOZHI K/OneDrive/Documents/Intern/S8- project/S8-Project/src/main/resources/static/images/" + file.getOriginalFilename()));
            } 
            catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        } 
        else {
            throw new RuntimeException("Invalid file type");
        }
        return url;
    }

}
