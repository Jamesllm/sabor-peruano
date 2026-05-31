package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.activo = true OR r.activo IS NULL ORDER BY r.id DESC")
    List<Reserva> findAllActive();

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.activo = true OR r.activo IS NULL")
    long countAllActive();

    List<Reserva> findAllByOrderByIdDesc();
}
