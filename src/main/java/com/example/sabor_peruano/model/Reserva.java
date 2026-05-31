package com.example.sabor_peruano.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    private String fechaHora;

    private Integer pax;

    private String mesa;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "reserva_platillo", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "platillo")
    private List<String> platillos;

    private String estado;

    private Boolean activo = true;

    public Reserva() {}

    public Reserva(Long id, String cliente, String fechaHora, Integer pax, String mesa, List<String> platillos, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.pax = pax;
        this.mesa = mesa;
        this.platillos = platillos;
        this.estado = estado;
    }

    public Reserva(String cliente, String fechaHora, Integer pax, String mesa, List<String> platillos, String estado) {
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.pax = pax;
        this.mesa = mesa;
        this.platillos = platillos;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
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
