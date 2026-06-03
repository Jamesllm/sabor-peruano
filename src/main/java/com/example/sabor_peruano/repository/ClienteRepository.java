package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByDocumento(String documento);

    @Query("SELECT c FROM Cliente c WHERE c.activo = true OR c.activo IS NULL ORDER BY c.nombre ASC")
    List<Cliente> findAllActive();

    List<Cliente> findAllByOrderByIdDesc();
}
