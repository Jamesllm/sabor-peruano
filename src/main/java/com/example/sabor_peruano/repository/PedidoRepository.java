package com.example.sabor_peruano.repository;

import com.example.sabor_peruano.model.Mesa;
import com.example.sabor_peruano.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.mesa = ?1 AND p.estado != 'Pagado' AND p.activo = true")
    Optional<Pedido> findActiveByMesa(Mesa mesa);
}
