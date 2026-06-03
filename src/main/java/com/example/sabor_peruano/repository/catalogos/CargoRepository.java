package com.example.sabor_peruano.repository.catalogos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sabor_peruano.model.catalogos.Cargo;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT c FROM Cargo c WHERE c.activo = true OR c.activo IS NULL ORDER BY c.nombre ASC")
    List<Cargo> findAllActive();

    List<Cargo> findAllByOrderByIdDesc();
}
