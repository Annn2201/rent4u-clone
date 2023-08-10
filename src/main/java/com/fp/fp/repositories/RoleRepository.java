package com.fp.fp.repositories;

import com.fp.fp.models.Roles;
import com.sun.jdi.LongValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
