package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    boolean existsByNumero(String numero);

    @Query("SELECT m FROM Mesa m WHERE m.activo = true OR m.activo IS NULL ORDER BY m.numero DESC")
    List<Mesa> findAllActive();

    @Query("SELECT COUNT(m) FROM Mesa m WHERE m.activo = true OR m.activo IS NULL")
    long countAllActive();

    List<Mesa> findAllByOrderByIdDesc();
}
