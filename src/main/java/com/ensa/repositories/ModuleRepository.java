package com.ensa.repositories;

import com.ensa.entity.Module;
import com.ensa.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {

    List<Module> findAllByProf(Prof prof);
}
