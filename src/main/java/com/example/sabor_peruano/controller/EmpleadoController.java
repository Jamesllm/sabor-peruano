package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.Reserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sabor_peruano.repository.PlatilloRepository;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    private final PlatilloRepository platilloRepository;

    public EmpleadoController(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    @GetMapping("")
    public String operaciones(Model model) {
        model.addAttribute("proximasLlegadas", getProximasLlegadas());
        model.addAttribute("ocupacionActual", 78);
        return "empleado/operaciones";
    }

    @GetMapping("/mesas")
    public String mesas(Model model) {
        return "empleado/mesas";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menu", platilloRepository.findAllActive());
        return "empleado/menu";
    }

    private List<Reserva> getProximasLlegadas() {
        return Arrays.asList(
            new Reserva(1L, "Carlos Mendoza", "20:00", 2, "Mesa 04", Arrays.asList("Ceviche"), "Confirmada"),
            new Reserva(2L, "Ana Lucía", "20:30", 5, "Mesa 12", Arrays.asList("Lomo Saltado", "Tacu Tacu"), "Confirmada"),
            new Reserva(3L, "Pedro Sánchez", "21:00", 3, "Bar 02", Arrays.asList("Pisco Sour"), "Confirmada"),
            new Reserva(4L, "Mónica Ruiz", "21:15", 2, "Mesa 08", Arrays.asList("Ají de Gallina"), "Confirmada"),
            new Reserva(5L, "Luis Alberto", "21:30", 4, "Mesa 01", Arrays.asList("Causa Limeña", "Chicha"), "Confirmada"),
            new Reserva(6L, "Familia Rivas", "22:00", 8, "Terraza VIP", Arrays.asList("Parrillada Mar y Tierra"), "Confirmada")
        );
    }
}
