package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    @Query("SELECT e FROM Empleado e WHERE e.activo = true OR e.activo IS NULL ORDER BY e.id DESC")
    List<Empleado> findAllActive();

    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.activo = true OR e.activo IS NULL")
    long countAllActive();

    List<Empleado> findAllByOrderByIdDesc();
}
