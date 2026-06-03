package com.example.sabor_peruano.repository.catalogos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sabor_peruano.model.catalogos.Ubicacion;

import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT u FROM Ubicacion u WHERE u.activo = true OR u.activo IS NULL ORDER BY u.nombre ASC")
    List<Ubicacion> findAllActive();

    List<Ubicacion> findAllByOrderByIdDesc();
}
