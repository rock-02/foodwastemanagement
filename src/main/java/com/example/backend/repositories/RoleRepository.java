package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{
    
}
