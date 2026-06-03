package com.example.sabor_peruano.repository.catalogos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sabor_peruano.model.catalogos.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT c FROM Categoria c WHERE c.activo = true OR c.activo IS NULL ORDER BY c.nombre ASC")
    List<Categoria> findAllActive();

    List<Categoria> findAllByOrderByIdDesc();
}
