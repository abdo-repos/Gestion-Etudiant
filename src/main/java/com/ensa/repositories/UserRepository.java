package com.ensa.repositories;

import com.ensa.entity.User;
import com.ensa.enums.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByCin(String cin);

    List<User> findAllByRole(Roles role);
}
