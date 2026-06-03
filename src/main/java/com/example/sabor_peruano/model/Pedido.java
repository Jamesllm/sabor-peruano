package com.example.sabor_peruano.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pedido_platillo", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "platillo")
    private List<String> platillos;

    private String estado; // E.g., "Pendiente", "Preparando", "Entregado", "Pagado"

    private Boolean activo = true;

    public Pedido() {}

    public Pedido(Mesa mesa, List<String> platillos, String estado) {
        this.mesa = mesa;
        this.platillos = platillos;
        this.estado = estado;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<String> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(List<String> platillos) {
        this.platillos = platillos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
