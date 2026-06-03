package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.*;
import com.example.sabor_peruano.model.catalogos.Cargo;
import com.example.sabor_peruano.model.catalogos.Categoria;
import com.example.sabor_peruano.model.catalogos.EstadoReserva;
import com.example.sabor_peruano.model.catalogos.Ubicacion;
import com.example.sabor_peruano.repository.*;
import com.example.sabor_peruano.repository.catalogos.CargoRepository;
import com.example.sabor_peruano.repository.catalogos.CategoriaRepository;
import com.example.sabor_peruano.repository.catalogos.EstadoReservaRepository;
import com.example.sabor_peruano.repository.catalogos.UbicacionRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PlatilloRepository platilloRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private final UbicacionRepository ubicacionRepository;
    private final CargoRepository cargoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoReservaRepository estadoReservaRepository;
    private final ClienteRepository clienteRepository;

    public AdminController(PlatilloRepository platilloRepository,
                           EmpleadoRepository empleadoRepository,
                           ReservaRepository reservaRepository,
                           MesaRepository mesaRepository,
                           UbicacionRepository ubicacionRepository,
                           CargoRepository cargoRepository,
                           CategoriaRepository categoriaRepository,
                           EstadoReservaRepository estadoReservaRepository,
                           ClienteRepository clienteRepository) {
        this.platilloRepository = platilloRepository;
        this.empleadoRepository = empleadoRepository;
        this.reservaRepository = reservaRepository;
        this.mesaRepository = mesaRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.cargoRepository = cargoRepository;
        this.categoriaRepository = categoriaRepository;
        this.estadoReservaRepository = estadoReservaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("")
    public String dashboard(Model model) {
        addStatsToModel(model);
        model.addAttribute("mesas", mesaRepository.findAllActive());
        return "admin/dashboard";
    }

    // ==========================================
    // CRUD RESERVAS
    // ==========================================
    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAllByOrderByIdDesc());
        model.addAttribute("platillosDisponibles", platilloRepository.findAllActive());
        model.addAttribute("mesasDisponibles", mesaRepository.findAllActive());
        model.addAttribute("clientesDisponibles", clienteRepository.findAllActive());
        model.addAttribute("estadosDisponibles", estadoReservaRepository.findAllActive());
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

    // ==========================================
    // CRUD EQUIPO
    // ==========================================
    @GetMapping("/equipo")
    public String equipo(Model model) {
        model.addAttribute("equipo", empleadoRepository.findAllByOrderByIdDesc());
        model.addAttribute("cargosDisponibles", cargoRepository.findAllActive());
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

    // ==========================================
    // CRUD MENÚ (PLATILLOS)
    // ==========================================
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menu", platilloRepository.findAllByOrderByIdDesc());
        model.addAttribute("categoriasDisponibles", categoriaRepository.findAllActive());
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

    // ==========================================
    // CRUD MESAS
    // ==========================================
    @GetMapping("/mesas")
    public String mesas(Model model) {
        model.addAttribute("mesas", mesaRepository.findAllByOrderByIdDesc());
        model.addAttribute("ubicacionesDisponibles", ubicacionRepository.findAllActive());
        return "admin/mesas";
    }

    @PostMapping("/mesas/guardar")
    public String guardarMesa(@ModelAttribute Mesa mesa) {
        if (mesa.getActivo() == null) {
            mesa.setActivo(true);
        }
        mesaRepository.save(mesa);
        return "redirect:/admin/mesas";
    }

    @GetMapping("/mesas/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaRepository.findById(id).ifPresent(mesa -> {
            mesa.setActivo(false); // logical delete
            mesaRepository.save(mesa);
        });
        return "redirect:/admin/mesas";
    }

    @GetMapping("/mesas/activar/{id}")
    public String activarMesa(@PathVariable Long id) {
        mesaRepository.findById(id).ifPresent(mesa -> {
            mesa.setActivo(true);
            mesaRepository.save(mesa);
        });
        return "redirect:/admin/mesas";
    }

    // ==========================================
    // CRUD CLIENTES
    // ==========================================
    @GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAllByOrderByIdDesc());
        return "admin/clientes";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        if (cliente.getActivo() == null) {
            cliente.setActivo(true);
        }
        clienteRepository.save(cliente);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteRepository.findById(id).ifPresent(cliente -> {
            cliente.setActivo(false);
            clienteRepository.save(cliente);
        });
        return "redirect:/admin/clientes";
    }

    @GetMapping("/clientes/activar/{id}")
    public String activarCliente(@PathVariable Long id) {
        clienteRepository.findById(id).ifPresent(cliente -> {
            cliente.setActivo(true);
            clienteRepository.save(cliente);
        });
        return "redirect:/admin/clientes";
    }

    // ==========================================
    // GESTIÓN DE CATÁLOGOS CENTRALIZADOS
    // ==========================================
    @GetMapping("/catalogos")
    public String catalogos(Model model) {
        model.addAttribute("ubicaciones", ubicacionRepository.findAllByOrderByIdDesc());
        model.addAttribute("cargos", cargoRepository.findAllByOrderByIdDesc());
        model.addAttribute("categorias", categoriaRepository.findAllByOrderByIdDesc());
        model.addAttribute("estadosReserva", estadoReservaRepository.findAllByOrderByIdDesc());
        return "admin/catalogos";
    }

    // Ubicaciones
    @PostMapping("/catalogos/ubicacion/guardar")
    public String guardarUbicacion(@ModelAttribute Ubicacion ubicacion) {
        if (ubicacion.getActivo() == null) ubicacion.setActivo(true);
        ubicacionRepository.save(ubicacion);
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/ubicacion/eliminar/{id}")
    public String eliminarUbicacion(@PathVariable Long id) {
        ubicacionRepository.findById(id).ifPresent(u -> {
            u.setActivo(false);
            ubicacionRepository.save(u);
        });
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/ubicacion/activar/{id}")
    public String activarUbicacion(@PathVariable Long id) {
        ubicacionRepository.findById(id).ifPresent(u -> {
            u.setActivo(true);
            ubicacionRepository.save(u);
        });
        return "redirect:/admin/catalogos";
    }

    // Cargos
    @PostMapping("/catalogos/cargo/guardar")
    public String guardarCargo(@ModelAttribute Cargo cargo) {
        if (cargo.getActivo() == null) cargo.setActivo(true);
        cargoRepository.save(cargo);
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/cargo/eliminar/{id}")
    public String eliminarCargo(@PathVariable Long id) {
        cargoRepository.findById(id).ifPresent(c -> {
            c.setActivo(false);
            cargoRepository.save(c);
        });
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/cargo/activar/{id}")
    public String activarCargo(@PathVariable Long id) {
        cargoRepository.findById(id).ifPresent(c -> {
            c.setActivo(true);
            cargoRepository.save(c);
        });
        return "redirect:/admin/catalogos";
    }

    // Categorias
    @PostMapping("/catalogos/categoria/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        if (categoria.getActivo() == null) categoria.setActivo(true);
        categoriaRepository.save(categoria);
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/categoria/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaRepository.findById(id).ifPresent(c -> {
            c.setActivo(false);
            categoriaRepository.save(c);
        });
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/categoria/activar/{id}")
    public String activarCategoria(@PathVariable Long id) {
        categoriaRepository.findById(id).ifPresent(c -> {
            c.setActivo(true);
            categoriaRepository.save(c);
        });
        return "redirect:/admin/catalogos";
    }

    // Estados de Reserva
    @PostMapping("/catalogos/estado-reserva/guardar")
    public String guardarEstadoReserva(@ModelAttribute EstadoReserva estadoReserva) {
        if (estadoReserva.getActivo() == null) estadoReserva.setActivo(true);
        estadoReservaRepository.save(estadoReserva);
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/estado-reserva/eliminar/{id}")
    public String eliminarEstadoReserva(@PathVariable Long id) {
        estadoReservaRepository.findById(id).ifPresent(er -> {
            er.setActivo(false);
            estadoReservaRepository.save(er);
        });
        return "redirect:/admin/catalogos";
    }

    @GetMapping("/catalogos/estado-reserva/activar/{id}")
    public String activarEstadoReserva(@PathVariable Long id) {
        estadoReservaRepository.findById(id).ifPresent(er -> {
            er.setActivo(true);
            estadoReservaRepository.save(er);
        });
        return "redirect:/admin/catalogos";
    }

    // ==========================================
    // STATS / METRICS HELPERS
    // ==========================================
    private void addStatsToModel(Model model) {
        model.addAttribute("totalReservas", String.valueOf(reservaRepository.countAllActive()));
        
        // Calcular ventas reales
        List<Reserva> activeReservas = reservaRepository.findAllActive();
        List<Platillo> allPlatillos = platilloRepository.findAll();
        Map<String, BigDecimal> platilloPrices = allPlatillos.stream()
            .collect(Collectors.toMap(Platillo::getNombre, Platillo::getPrecio, (p1, p2) -> p1));

        BigDecimal totalSales = BigDecimal.ZERO;
        for (Reserva r : activeReservas) {
            if (r.getPlatillos() != null && "Confirmada".equals(r.getEstado())) {
                for (String platilloName : r.getPlatillos()) {
                    BigDecimal price = platilloPrices.get(platilloName);
                    if (price != null) {
                        totalSales = totalSales.add(price);
                    }
                }
            }
        }

        model.addAttribute("ventasMes", String.format("S/ %,.2f", totalSales));
        model.addAttribute("platosActivos", String.valueOf(platilloRepository.countAllActive()));
        model.addAttribute("equipoActivo", String.valueOf(empleadoRepository.countAllActive()));
    }
}
