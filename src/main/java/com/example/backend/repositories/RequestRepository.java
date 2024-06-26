package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
    

}
