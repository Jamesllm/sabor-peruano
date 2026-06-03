package com.example.sabor_peruano.repository.catalogos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sabor_peruano.model.catalogos.EstadoReserva;

import java.util.List;

public interface EstadoReservaRepository extends JpaRepository<EstadoReserva, Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT er FROM EstadoReserva er WHERE er.activo = true OR er.activo IS NULL ORDER BY er.nombre ASC")
    List<EstadoReserva> findAllActive();

    List<EstadoReserva> findAllByOrderByIdDesc();
}
