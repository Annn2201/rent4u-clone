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
public class Types {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_id")
    private long typeId;
    @Basic
    @Column(name = "type_name")
    private String typeName;
    @Basic
    @Column(name = "sit")
    private Integer sit;
    @OneToMany(mappedBy = "carType")
    private List<Cars> cars;


}
