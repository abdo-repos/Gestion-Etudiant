package com.ensa.repositories;

import com.ensa.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfRepository extends JpaRepository<Prof,Long> {

    Optional<Prof> findByEmail(String email);
}
