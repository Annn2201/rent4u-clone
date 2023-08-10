package com.fp.fp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<Users> users = new ArrayList<>();

    public Roles(String name) {
        this.name = name;
    }
}
