package com.example.sabor_peruano.controller;

import com.example.sabor_peruano.model.Platillo;
import com.example.sabor_peruano.repository.PlatilloRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlatilloController {

    private final PlatilloRepository platilloRepository;

    public PlatilloController(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("platillos", platilloRepository.findAllActive());
        model.addAttribute("titulo", "Nuestra Tradición");
        return "index";
    }

    @GetMapping("/platillos")
    public String listar(Model model) {
        model.addAttribute("platillos", platilloRepository.findAll());
        return "platillos";
    }

    @GetMapping("/platillos/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("platillo", new Platillo());
        return "formulario";
    }

    @PostMapping("/platillos/guardar")
    public String guardar(@ModelAttribute Platillo platillo) {
        platilloRepository.save(platillo);
        return "redirect:/platillos";
    }

    @GetMapping("/platillos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        try {
            Platillo platillo = platilloRepository.findById(id).get();
            model.addAttribute("platillo", platillo);
            return "formulario";
        } catch (Exception e) {
            return "redirect:/platillos";
        }
    }

    @GetMapping("/platillos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        platilloRepository.deleteById(id);
        return "redirect:/platillos";
    }
}
