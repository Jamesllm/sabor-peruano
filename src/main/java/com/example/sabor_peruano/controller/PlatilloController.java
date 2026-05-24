package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.Platillo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class PlatilloController {

    @GetMapping("/bienvenida")
    public String mensajeBienvenida(Model model) {
        model.addAttribute("mensaje", "¡Bienvenido al sistema de Sabor Peruano! El mejor restaurante de comida criolla.");
        return "bienvenida"; // Busca templates/bienvenida.html
    }

    @GetMapping("/")
    public String inicio(Model model) {
        List<Platillo> platillos = Arrays.asList(
                new Platillo(1, "Empanaditas Criollas", "Tradicionales de carne con un toque de ají panca y salsa huancaína.", new BigDecimal("25.00"), "Entrada", "empanaditas.jpg", true),
                new Platillo(2, "Lomo Saltado", "Jugosos trozos de lomo fino salteados al wok con cebolla, tomate y ají amarillo.", new BigDecimal("55.00"), "Fondo", "lomo-saltado.jpg", true),
                new Platillo(3, "Ají de Gallina", "Cremosa preparación con base de ají amarillo, nueces y pollo deshilachado.", new BigDecimal("42.00"), "Fondo", "aji-de-gallina.jpg", true),
                new Platillo(4, "Suspiro a la Limeña", "Clásico manjar blanco cubierto de merengue al oporto y canela.", new BigDecimal("18.00"), "Postre", "suspiro-limena.jpg", true),
                new Platillo(5, "Chicha Morada", "Elaborada artesanalmente con maíz morado, piña, manzana y canela.", new BigDecimal("12.00"), "Bebida", "chicha-morada.jpg", true),
                new Platillo(6, "Pisco Sour", "Nuestro cóctel bandera, equilibrado entre Pisco Quebranta y zumo de limón.", new BigDecimal("28.00"), "Bebida", "pisco-sour.jpg", true),
                new Platillo(7, "Ceviche de Pescado", "Dados de pescado fresco marinados en limón, acompañados de camote y choclo.", new BigDecimal("48.00"), "Entrada", "ceviche.jpg", true),
                new Platillo(8, "Anticuchos de Corazón", "Brochetas de corazón de res maceradas en ají panca, servidas con papas doradas.", new BigDecimal("32.00"), "Entrada", "anticuchos.jpg", true),
                new Platillo(9, "Arroz con Pato", "Tradicional arroz norteño con pato tierno y loche.", new BigDecimal("62.00"), "Fondo", "arroz-pato.jpg", true)
        );
        
        model.addAttribute("titulo", "Nuestra Tradición");
        model.addAttribute("platillos", platillos);
        return "index";
    }

    @GetMapping("/registrar")
    public String registrarPlatillo(
            @RequestParam String nombre,
            @RequestParam BigDecimal precio,
            @RequestParam String categoria,
            Model model) {

        String mensaje = "Confirmación: El platillo '" + nombre + "' de la categoría '" + categoria +
                "' ha sido registrado con un precio de S/ " + precio + ".";
        model.addAttribute("mensaje", mensaje);
        return "confirmacion"; // Busca templates/confirmacion.html
    }
}
