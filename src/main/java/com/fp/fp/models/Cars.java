package com.fp.fp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cars {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private long carId;
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brands brand;
    @ManyToOne
    @JoinColumn(name = "car_type")
    private Types carType;
    @Basic
    @Column(name = "car_name")
    private String carName;
    @Basic
    @Column(name = "color")
    private String color;
    @Basic
    @Column(name = "rent_cost")
    private Double rentCost;
    @ManyToOne
    @JoinColumn(name = "user")
    private Users users;
    @Column(name = "car_image_url")
    private String carImageUrl;

}
