package com.ensa.repositories;

import com.ensa.entity.DemandeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeServiceRepository extends JpaRepository<DemandeService,Long> {
}
