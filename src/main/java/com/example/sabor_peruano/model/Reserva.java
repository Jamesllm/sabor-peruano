package com.example.sabor_peruano.model;

import java.util.List;

public class Reserva {
    private String id;
    private String cliente;
    private String fechaHora;
    private Integer pax;
    private String mesa;
    private List<String> platillos;
    private String estado;

    public Reserva() {}

    public Reserva(String id, String cliente, String fechaHora, Integer pax, String mesa, List<String> platillos, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.pax = pax;
        this.mesa = mesa;
        this.platillos = platillos;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
