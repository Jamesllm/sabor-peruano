package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.Empleado;
import com.example.sabor_peruano.model.Platillo;
import com.example.sabor_peruano.model.Reserva;
import com.example.sabor_peruano.repository.PlatilloRepository;
import com.example.sabor_peruano.repository.EmpleadoRepository;
import com.example.sabor_peruano.repository.ReservaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PlatilloRepository platilloRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ReservaRepository reservaRepository;

    public AdminController(PlatilloRepository platilloRepository,
                           EmpleadoRepository empleadoRepository,
                           ReservaRepository reservaRepository) {
        this.platilloRepository = platilloRepository;
        this.empleadoRepository = empleadoRepository;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("")
    public String dashboard(Model model) {
        addStatsToModel(model);
        return "admin/dashboard";
    }

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAllByOrderByIdDesc());
        model.addAttribute("platillosDisponibles", platilloRepository.findAllActive());
        return "admin/reservas";
    }

    @PostMapping("/reservas/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        if (reserva.getActivo() == null) {
            reserva.setActivo(true);
        }
        reservaRepository.save(reserva);
        return "redirect:/admin/reservas";
    }

    @GetMapping("/reservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            reserva.setActivo(false); // logical delete
            reservaRepository.save(reserva);
        });
        return "redirect:/admin/reservas";
    }

    @GetMapping("/reservas/activar/{id}")
    public String activarReserva(@PathVariable Long id) {
        reservaRepository.findById(id).ifPresent(reserva -> {
            reserva.setActivo(true);
            reservaRepository.save(reserva);
        });
        return "redirect:/admin/reservas";
    }

    @GetMapping("/equipo")
    public String equipo(Model model) {
        model.addAttribute("equipo", empleadoRepository.findAllByOrderByIdDesc());
        return "admin/equipo";
    }

    @PostMapping("/equipo/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        if (empleado.getActivo() == null) {
            empleado.setActivo(true);
        }
        empleadoRepository.save(empleado);
        return "redirect:/admin/equipo";
    }

    @GetMapping("/equipo/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoRepository.findById(id).ifPresent(empleado -> {
            empleado.setActivo(false); // logical delete
            empleadoRepository.save(empleado);
        });
        return "redirect:/admin/equipo";
    }

    @GetMapping("/equipo/activar/{id}")
    public String activarEmpleado(@PathVariable Long id) {
        empleadoRepository.findById(id).ifPresent(empleado -> {
            empleado.setActivo(true);
            empleadoRepository.save(empleado);
        });
        return "redirect:/admin/equipo";
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

    private void addStatsToModel(Model model) {
        model.addAttribute("totalReservas", String.valueOf(reservaRepository.countAllActive()));
        model.addAttribute("ventasMes", "S/ 45,200");
        model.addAttribute("platosActivos", String.valueOf(platilloRepository.countAllActive()));
        model.addAttribute("equipoActivo", String.valueOf(empleadoRepository.countAllActive()));
    }
}
