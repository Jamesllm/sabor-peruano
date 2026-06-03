package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.*;
import com.example.sabor_peruano.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    private final PlatilloRepository platilloRepository;
    private final MesaRepository mesaRepository;
    private final PedidoRepository pedidoRepository;
    private final ReservaRepository reservaRepository;

    public EmpleadoController(PlatilloRepository platilloRepository,
                              MesaRepository mesaRepository,
                              PedidoRepository pedidoRepository,
                              ReservaRepository reservaRepository) {
        this.platilloRepository = platilloRepository;
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("")
    public String operaciones(Model model) {
        List<Reserva> activeReservas = reservaRepository.findAllActive();
        model.addAttribute("proximasLlegadas", activeReservas);
        
        // Calcular porcentaje de ocupación real: (mesas ocupadas / total mesas) * 100
        long totalMesas = mesaRepository.countAllActive();
        long ocupadas = mesaRepository.findAllActive().stream()
            .filter(m -> "Ocupada".equals(m.getEstado()))
            .count();
        long pct = totalMesas > 0 ? (ocupadas * 100) / totalMesas : 0;
        model.addAttribute("ocupacionActual", pct);
        
        return "empleado/operaciones";
    }

    @GetMapping("/mesas")
    public String mesas(Model model) {
        List<Mesa> mesas = mesaRepository.findAllActive();
        model.addAttribute("mesas", mesas);
        model.addAttribute("platillos", platilloRepository.findAllActive());
        
        // Rastrear pedidos activos para cada mesa (asociar mesaId -> Pedido)
        Map<Long, Pedido> pedidosActivos = mesas.stream()
            .filter(m -> "Ocupada".equals(m.getEstado()))
            .collect(Collectors.toMap(
                Mesa::getId,
                m -> pedidoRepository.findActiveByMesa(m).orElse(new Pedido(m, new java.util.ArrayList<>(), "Pendiente")),
                (p1, p2) -> p1
            ));
        model.addAttribute("pedidosActivos", pedidosActivos);
        
        return "empleado/mesas";
    }

    @PostMapping("/mesas/ocupar/{id}")
    public String ocuparMesa(@PathVariable Long id) {
        mesaRepository.findById(id).ifPresent(mesa -> {
            mesa.setEstado("Ocupada");
            mesaRepository.save(mesa);
            
            // Inicializar un pedido vacío para esta mesa si no tiene uno activo
            if (pedidoRepository.findActiveByMesa(mesa).isEmpty()) {
                pedidoRepository.save(new Pedido(mesa, new java.util.ArrayList<>(), "Pendiente"));
            }
        });
        return "redirect:/empleado/mesas";
    }

    @PostMapping("/mesas/liberar/{id}")
    public String liberarMesa(@PathVariable Long id) {
        mesaRepository.findById(id).ifPresent(mesa -> {
            mesa.setEstado("Disponible");
            mesaRepository.save(mesa);
            
            // Marcar el pedido activo como pagado / inactivo
            pedidoRepository.findActiveByMesa(mesa).ifPresent(pedido -> {
                pedido.setEstado("Pagado");
                pedido.setActivo(false);
                pedidoRepository.save(pedido);
            });
        });
        return "redirect:/empleado/mesas";
    }

    @PostMapping("/mesas/pedido/guardar")
    public String guardarPedido(@RequestParam("mesaId") Long mesaId,
                                @RequestParam(value = "platillos", required = false) List<String> platillos) {
        mesaRepository.findById(mesaId).ifPresent(mesa -> {
            pedidoRepository.findActiveByMesa(mesa).ifPresent(pedido -> {
                if (platillos != null) {
                    pedido.setPlatillos(platillos);
                } else {
                    pedido.setPlatillos(new java.util.ArrayList<>());
                }
                pedidoRepository.save(pedido);
            });
        });
        return "redirect:/empleado/mesas";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menu", platilloRepository.findAllActive());
        return "empleado/menu";
    }
}
