package com.fp.fp.dtos;

import com.fp.fp.models.Cars;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDTO {
    private Long typeId;
    private String typeName;
    private Integer sit;
    private List<Cars> cars;
}
