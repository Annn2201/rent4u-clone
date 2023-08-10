package com.fp.fp.services.Impl;

import com.fp.fp.dtos.TypeDTO;
import com.fp.fp.models.Types;
import com.fp.fp.repositories.TypeRepository;
import com.fp.fp.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    @Override
    public List<TypeDTO> getAllCarType() {
        List<Types> carTypes = typeRepository.findAll();
        return carTypes.stream().map(carType -> TypeDTO.builder()
                .typeId(carType.getTypeId())
                .typeName(carType.getTypename())
                .cars(carType.getCars())
                .build()).collect(Collectors.toList());
    }
}
