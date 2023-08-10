package com.fp.fp.repositories;

import com.fp.fp.models.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Types, Long> {
}
