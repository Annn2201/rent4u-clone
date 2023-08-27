package com.fp.fp.dtos;

import com.fp.fp.models.Brands;
import com.fp.fp.models.Types;
import com.fp.fp.models.Users;
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
    private Double rentCost;
    private Users users;
    private String carImageUrl;
}
