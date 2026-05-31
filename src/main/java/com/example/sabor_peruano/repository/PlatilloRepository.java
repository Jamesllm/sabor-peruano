package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT p FROM Platillo p WHERE p.activo = true OR p.activo IS NULL ORDER BY p.id DESC")
    List<Platillo> findAllActive();

    @Query("SELECT COUNT(p) FROM Platillo p WHERE p.activo = true OR p.activo IS NULL")
    long countAllActive();

    List<Platillo> findAllByOrderByIdDesc();
}
