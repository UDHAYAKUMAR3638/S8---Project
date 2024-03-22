package com.trace.product.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.trace.product.Model.Composition;

import lombok.Data;

@Data
public class InboundDto {
    private String lotNo;
    private String materialComposition;
    private String invoiceNo;
    private String sellerName;
    private Date invoiceDate;
    private String addedBy;
    private String totalQuantity;
    private String remainingQuantity;
}
