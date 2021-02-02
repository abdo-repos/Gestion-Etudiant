package com.ensa.repositories;

import com.ensa.entity.AllUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllUserRepository extends JpaRepository<AllUser,Long> {

    AllUser findByCin(String cin);
}
