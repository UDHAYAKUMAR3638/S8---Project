package com.trace.product.Model;

import com.trace.product.Dto.CompositionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Composition {
    String materialType;
    Double percentage;

    public Composition(CompositionDto newComposition) {
        this.materialType = newComposition.getMaterialType();
        this.percentage = Double.parseDouble(newComposition.getPercentage());
    }
}
