package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.Empleado;
import com.example.sabor_peruano.model.Platillo;
import com.example.sabor_peruano.model.Reserva;
import com.example.sabor_peruano.repository.PlatilloRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PlatilloRepository platilloRepository;

    public AdminController(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    @GetMapping("")
    public String dashboard(Model model) {
        addStatsToModel(model);
        return "admin/dashboard";
    }

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservas", getReservasSimuladas());
        return "admin/reservas";
    }

    @GetMapping("/equipo")
    public String equipo(Model model) {
        model.addAttribute("equipo", getEquipoSimulado());
        return "admin/equipo";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menu", platilloRepository.findAllByOrderByIdDesc());
        return "admin/menu";
    }

    @PostMapping("/menu/guardar")
    public String guardarPlatillo(@ModelAttribute Platillo platillo) {
        if (platillo.getActivo() == null) {
            platillo.setActivo(true);
        }
        platilloRepository.save(platillo);
        return "redirect:/admin/menu";
    }

    @GetMapping("/menu/eliminar/{id}")
    public String eliminarPlatillo(@PathVariable Long id) {
        platilloRepository.findById(id).ifPresent(platillo -> {
            platillo.setActivo(false); // Eliminación lógica
            platilloRepository.save(platillo);
        });
        return "redirect:/admin/menu";
    }

    @GetMapping("/menu/activar/{id}")
    public String activarPlatillo(@PathVariable Long id) {
        platilloRepository.findById(id).ifPresent(platillo -> {
            platillo.setActivo(true);
            platilloRepository.save(platillo);
        });
        return "redirect:/admin/menu";
    }

    // Métodos auxiliares para datos simulados
    private void addStatsToModel(Model model) {
        model.addAttribute("totalReservas", "1,284");
        model.addAttribute("ventasMes", "S/ 45,200");
        model.addAttribute("platosActivos", String.valueOf(platilloRepository.countAllActive()));
        model.addAttribute("equipoActivo", "12");
    }

    private List<Reserva> getReservasSimuladas() {
        return Arrays.asList(
            new Reserva("#S001", "Juan Pérez", "05 Abr, 20:30", 2, "Mesa 04", Arrays.asList("Lomo Saltado", "Pisco Sour"), "Confirmada"),
            new Reserva("#S002", "Maria Garcia", "05 Abr, 21:00", 4, "Mesa 10", Arrays.asList("Ají de Gallina", "Chicha Morada"), "Pendiente"),
            new Reserva("#S003", "Ricardo Palma", "06 Abr, 19:30", 2, "Mesa 01", Arrays.asList("Ceviche", "Inca Kola"), "Confirmada"),
            new Reserva("#S004", "Sofía Velásquez", "06 Abr, 20:00", 6, "Mesa VIP", Arrays.asList("Seco de Cabrito", "Vino Tinto"), "Confirmada"),
            new Reserva("#S005", "Carlos Torres", "07 Abr, 13:00", 2, "Terraza 02", Arrays.asList("Arroz con Pato"), "Pendiente"),
            new Reserva("#S006", "Lucía Méndez", "07 Abr, 14:30", 3, "Mesa 05", Arrays.asList("Anticuchos", "Cerveza"), "Confirmada")
        );
    }

    private List<Empleado> getEquipoSimulado() {
        return Arrays.asList(
            new Empleado("1", "Elena Lopez", "Capitán de Meseros"),
            new Empleado("2", "Marco Ruiz", "Hostess"),
            new Empleado("3", "Carmen Rosa", "Chef Ejecutivo"),
            new Empleado("4", "José Arana", "Barman"),
            new Empleado("5", "Patricia Soler", "Sommelier"),
            new Empleado("6", "Luis Guzmán", "Mesero VIP")
        );
    }

    private List<Platillo> getMenuSimulado() {
        return Arrays.asList(
            // new Platillo(1, "Lomo Saltado", "Platos de Fondo", new BigDecimal("55.00"), "Fondos", null, true),
            // new Platillo(2, "Ceviche Clásico", "Entradas", new BigDecimal("48.00"), "Entradas", null, true),
            // new Platillo(3, "Pisco Sour", "Bebidas", new BigDecimal("28.00"), "Bebidas", null, true),
            // new Platillo(4, "Ají de Gallina", "Platos de Fondo", new BigDecimal("42.00"), "Fondos", null, true),
            // new Platillo(5, "Tacu Tacu con Lomo", "Platos de Fondo", new BigDecimal("58.00"), "Fondos", null, true),
            // new Platillo(6, "Causa Limeña", "Entradas", new BigDecimal("35.00"), "Entradas", null, true)
        );
    }
}
