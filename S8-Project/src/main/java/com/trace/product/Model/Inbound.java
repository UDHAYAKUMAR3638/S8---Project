package com.trace.product.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Inbound {
    @Id
    String _id;
    String lotNo;
    ArrayList<Composition> materialComposition;
    // Double quantity;
    Integer invoiceNo;
    String sellerName;
    Date invoiceDate;
    String addedBy;
    Double totalQuantity;
    Double remainingQuantity;
    List<String> images;
    Integer currentImage;
    
    public Inbound() {
        this.materialComposition = new ArrayList<>();
        this.images = new ArrayList<>();
        this.currentImage = 0;
    }
}
