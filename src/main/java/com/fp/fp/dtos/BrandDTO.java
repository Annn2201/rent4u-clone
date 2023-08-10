package com.fp.fp.dtos;

import com.fp.fp.models.Cars;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {
    private Long brandId;
    private String brandName;
    private List<Cars> cars;
}
