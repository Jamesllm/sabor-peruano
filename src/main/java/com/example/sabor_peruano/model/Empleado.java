package com.example.sabor_peruano.model;

public class Empleado {
    private String id;
    private String nombre;
    private String cargo;
    private String iniciales;

    public Empleado() {}

    public Empleado(String id, String nombre, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.iniciales = obtenerIniciales(nombre);
    }

    private String obtenerIniciales(String nombre) {
        if (nombre == null || nombre.isEmpty()) return "";
        String[] parts = nombre.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < Math.min(parts.length, 2); i++) {
            if (!parts[i].isEmpty()) {
                res.append(parts[i].charAt(0));
            }
        }
        return res.toString().toUpperCase();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.iniciales = obtenerIniciales(nombre);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIniciales() {
        return iniciales;
    }
}
