package com.fp.fp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Brands {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "brand_id")
    private Long brandId;
    @Basic
    @Column(name = "brandName")
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private List<Cars> cars;


}
