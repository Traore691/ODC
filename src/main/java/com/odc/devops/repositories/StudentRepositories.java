package com.odc.devops.repositories;

import com.odc.devops.entites.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositories extends JpaRepository<Students, Long> {

    // Recherche par nom (insensible à la casse)
    List<Students> findByNomContainingIgnoreCase(String nom);

    // Recherche par prénom (insensible à la casse)
    List<Students> findByPrenomContainingIgnoreCase(String prenom);
}