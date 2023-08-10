package com.fp.fp.dtos;

import com.fp.fp.models.Brands;
import com.fp.fp.models.Types;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {
    private long carId;
    private Brands brand;
    private Types carType;
    private String carName;
    private String color;
    private Double lentCost;
}
