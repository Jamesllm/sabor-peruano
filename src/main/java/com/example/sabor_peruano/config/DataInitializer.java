package com.example.sabor_peruano.config;

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

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PlatilloRepository platilloRepository;
    private final MesaRepository mesaRepository;
    private final ReservaRepository reservaRepository;
    private final UbicacionRepository ubicacionRepository;
    private final CargoRepository cargoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoReservaRepository estadoReservaRepository;
    private final ClienteRepository clienteRepository;

    public DataInitializer(PlatilloRepository platilloRepository,
                           MesaRepository mesaRepository,
                           ReservaRepository reservaRepository,
                           UbicacionRepository ubicacionRepository,
                           CargoRepository cargoRepository,
                           CategoriaRepository categoriaRepository,
                           EstadoReservaRepository estadoReservaRepository,
                           ClienteRepository clienteRepository) {
        this.platilloRepository = platilloRepository;
        this.mesaRepository = mesaRepository;
        this.reservaRepository = reservaRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.cargoRepository = cargoRepository;
        this.categoriaRepository = categoriaRepository;
        this.estadoReservaRepository = estadoReservaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Limpiar platillos existentes para repoblarlos de cero
        platilloRepository.deleteAll();

        // Seeding Platillos
        insertIfNotFound("Ceviche Clásico", "Delicioso ceviche clásico de pescado marinado en limón con cebolla y ají limo.", new BigDecimal("48.00"), "Entrada", "ceviche.jpg", true);
        insertIfNotFound("Lomo Saltado", "Trozos de lomo de res saltados con cebolla, tomate, ají amarillo y papas fritas.", new BigDecimal("55.00"), "Fondo", "lomo-saltado.jpg", true);
        insertIfNotFound("Ají de Gallina", "Crema de ají amarillo con gallina deshilachada, papas y huevo duro.", new BigDecimal("42.00"), "Fondo", "aji-de-gallina.jpg", true);
        insertIfNotFound("Anticuchos", "Brochetas de corazón de res marinado en ají panca acompañadas de papas doradas y choclo.", new BigDecimal("38.00"), "Entrada", "anticuchos.jpg", true);
        insertIfNotFound("Tacu Tacu con Lomo", "Mezcla de arroz y frejoles dorada a la sartén con lomo al jugo.", new BigDecimal("58.00"), "Fondo", "tacu-tacu.jpg", true);
        insertIfNotFound("Suspiro a la Limeña", "Postre tradicional limeño dulce y cremoso con merengue al oporto.", new BigDecimal("18.00"), "Postre", "suspiro-limena.jpg", true);
        insertIfNotFound("Pisco Sour", "El cóctel bandera del Perú, preparado con pisco, limón, jarabe de goma y clara de huevo.", new BigDecimal("28.00"), "Bebida", "pisco-sour.jpg", true);
        insertIfNotFound("Chicha Morada", "Bebida tradicional peruana hecha de maíz morado hervido con piña, manzana y especias.", new BigDecimal("12.00"), "Bebida", "chicha-morada.jpg", true);

        // Nuevos platos
        insertIfNotFound("Arroz con Mariscos", "Arroz sazonado con especias, mariscos frescos de la costa, pimientos y guisantes.", new BigDecimal("52.00"), "Fondo", "arroz-con-mariscos.jpg", true);
        insertIfNotFound("Seco de Cabrito", "Guiso tradicional norteño de cabrito tierno macerado en chicha de jora y culantro.", new BigDecimal("46.00"), "Fondo", "seco-de-cabrito.jpg", true);
        insertIfNotFound("Arroz con Pato", "Arroz verde cocido con culantro y cerveza, acompañado de tierna presa de pato.", new BigDecimal("54.00"), "Fondo", "arroz-pato.jpg", true);
        insertIfNotFound("Pachamanca a la Olla", "Combinación de carnes y tubérculos cocidos con hierbas andinas como el chincho.", new BigDecimal("58.00"), "Fondo", "pachamanca.jpg", true);
        insertIfNotFound("Chicharrón de Pescado", "Trozos de pescado fritos al panko, acompañados de yuca frita y salsa criolla.", new BigDecimal("36.00"), "Fondo", "chicharron-pescado.jpg", true);
        insertIfNotFound("Ají de Pollo", "Variante cremosa y suave del ají de gallina hecha con pechuga de pollo deshilachada.", new BigDecimal("39.00"), "Fondo", "aji-de-pollo.jpg", true);
        insertIfNotFound("Empanaditas de Carne", "Pequeñas empanadas crujientes rellenas de jugosa carne de res, pasas y aceituna.", new BigDecimal("24.00"), "Entrada", "empanaditas.jpg", true);
        insertIfNotFound("Helado de Lúcuma", "Helado artesanal elaborado con lúcuma fresca de los valles interandinos.", new BigDecimal("16.00"), "Postre", "helado-lucuma.jpg", true);
        insertIfNotFound("Limonada Maracuyá", "Refrescante limonada endulzada con la intensa pulpa de maracuyá fresca.", new BigDecimal("14.00"), "Bebida", "limonada-maracuya.jpg", true);
        insertIfNotFound("Tiramisú de Café Peruano", "Postre italiano clásico aromatizado con el mejor café orgánico de Chanchamayo.", new BigDecimal("22.00"), "Postre", "tiramisu.jpg", true);
        insertIfNotFound("Turrón de Doña Pepa", "Dulce tradicional limeño compuesto de palitos de harina horneados y miel de frutas.", new BigDecimal("15.00"), "Postre", "turron-dona-pepa.jpg", true);
        insertIfNotFound("Coulant de Chocolate", "Bizcocho caliente de chocolate peruano con corazón fundido de cacao al 70%.", new BigDecimal("20.00"), "Postre", "coulant-de-chocolate.jpg", true);
        insertIfNotFound("Crème Brûlée de Vainilla", "Clásica crema de vainilla con una crujiente capa superior de azúcar caramelizada.", new BigDecimal("19.00"), "Postre", "creme-brulee.jpg", true);
        insertIfNotFound("King Kong", "Dulce tradicional norteño con capas de galleta, manjar blanco y dulce de piña.", new BigDecimal("15.00"), "Postre", "KingKong.jpg", true);
        insertIfNotFound("Pisco Punch", "Trago histórico preparado con pisco, piña macerada, limón y jarabe.", new BigDecimal("26.00"), "Bebida", "PiscoPunch.jpg", true);
        insertIfNotFound("Cuba Libre", "Ron blanco, gaseosa cola y unas gotas de limón, servido con hielo.", new BigDecimal("22.00"), "Bebida", "Cuba.jpg", true);
        insertIfNotFound("Cosmopolitan", "Cóctel de vodka, triple sec, jugo de arándano y jugo de limón recién exprimido.", new BigDecimal("25.00"), "Bebida", "Cosmopolita.jpg", true);

        // Seeding Ubicaciones
        insertUbicacionIfNotFound("Salón Principal");
        insertUbicacionIfNotFound("Terraza");
        insertUbicacionIfNotFound("VIP");
        insertUbicacionIfNotFound("Bar");

        // Seeding Cargos
        insertCargoIfNotFound("Capitán de Meseros");
        insertCargoIfNotFound("Cocinero");
        insertCargoIfNotFound("Mozo");
        insertCargoIfNotFound("Cajero");
        insertCargoIfNotFound("Sommelier");
        insertCargoIfNotFound("Hostess");

        // Seeding Categorias
        insertCategoriaIfNotFound("Entrada");
        insertCategoriaIfNotFound("Fondo");
        insertCategoriaIfNotFound("Postre");
        insertCategoriaIfNotFound("Bebida");

        // Seeding Estados de Reserva
        insertEstadoReservaIfNotFound("Confirmada");
        insertEstadoReservaIfNotFound("Pendiente");
        insertEstadoReservaIfNotFound("Cancelada");

        // Seeding Clientes
        Cliente c1 = insertClienteIfNotFound("Carlos Mendoza", "72849182", "987654321", "carlos@example.com");
        Cliente c2 = insertClienteIfNotFound("Ana Lucía", "61938482", "912345678", "ana@example.com");
        Cliente c3 = insertClienteIfNotFound("Pedro Sánchez", "42839182", "934567812", "pedro@example.com");
        Cliente c4 = insertClienteIfNotFound("Mónica Ruiz", "51829381", "956781234", "monica@example.com");
        Cliente c5 = insertClienteIfNotFound("Luis Alberto", "32819283", "978123456", "luis@example.com");
        Cliente c6 = insertClienteIfNotFound("Familia Rivas", "20193848", "991234567", "rivas@example.com");

        // Seeding Mesas
        insertMesaIfNotFound("Mesa 01", 2, "Salón Principal", "Disponible");
        insertMesaIfNotFound("Mesa 02", 4, "Salón Principal", "Disponible");
        insertMesaIfNotFound("Mesa 03", 4, "Salón Principal", "Disponible");
        insertMesaIfNotFound("Mesa 04", 2, "Terraza", "Disponible");
        insertMesaIfNotFound("Mesa 05", 6, "Salón Principal", "Disponible");
        insertMesaIfNotFound("Mesa 08", 2, "Salón Principal", "Disponible");
        insertMesaIfNotFound("Mesa 12", 6, "Terraza", "Disponible");
        insertMesaIfNotFound("Bar 02", 2, "Bar", "Disponible");
        insertMesaIfNotFound("Terraza VIP", 8, "VIP", "Disponible");

        // Seeding Reservas
        if (reservaRepository.count() == 0) {
            reservaRepository.save(new Reserva(c1, "2026-06-03 20:00", 2, "Mesa 04", Arrays.asList("Ceviche Clásico", "Lomo Saltado", "Pisco Sour"), "Confirmada"));
            reservaRepository.save(new Reserva(c2, "2026-06-03 20:30", 5, "Mesa 12", Arrays.asList("Lomo Saltado", "Ají de Gallina", "Chicha Morada"), "Confirmada"));
            reservaRepository.save(new Reserva(c3, "2026-06-03 21:00", 3, "Bar 02", Arrays.asList("Anticuchos", "Pisco Sour", "Pisco Punch"), "Confirmada"));
            reservaRepository.save(new Reserva(c4, "2026-06-03 21:15", 2, "Mesa 08", Arrays.asList("Ají de Gallina", "Suspiro a la Limeña"), "Confirmada"));
            reservaRepository.save(new Reserva(c5, "2026-06-03 21:30", 4, "Mesa 01", Arrays.asList("Arroz con Mariscos", "Seco de Cabrito", "Chicha Morada"), "Confirmada"));
            reservaRepository.save(new Reserva(c6, "2026-06-03 22:00", 8, "Terraza VIP", Arrays.asList("Pachamanca a la Olla", "Arroz con Pato", "Chicharrón de Pescado", "Cosmopolitan"), "Confirmada"));
        }
    }

    private void insertIfNotFound(String nombre, String descripcion, BigDecimal precio, String categoriaName, String urlImagen, boolean disponibilidad) {
        if (!platilloRepository.existsByNombre(nombre)) {
            Categoria cat = categoriaRepository.findAll().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(categoriaName))
                .findFirst()
                .orElseGet(() -> categoriaRepository.save(new Categoria(categoriaName)));
            platilloRepository.save(new Platillo(null, nombre, descripcion, precio, cat, urlImagen, disponibilidad));
        }
    }

    private void insertMesaIfNotFound(String numero, Integer capacidad, String ubicacion, String estado) {
        if (!mesaRepository.existsByNumero(numero)) {
            mesaRepository.save(new Mesa(numero, capacidad, ubicacion, estado));
        }
    }

    private void insertUbicacionIfNotFound(String nombre) {
        if (!ubicacionRepository.existsByNombre(nombre)) {
            ubicacionRepository.save(new Ubicacion(nombre));
        }
    }

    private void insertCargoIfNotFound(String nombre) {
        if (!cargoRepository.existsByNombre(nombre)) {
            cargoRepository.save(new Cargo(nombre));
        }
    }

    private void insertCategoriaIfNotFound(String nombre) {
        if (!categoriaRepository.existsByNombre(nombre)) {
            categoriaRepository.save(new Categoria(nombre));
        }
    }

    private void insertEstadoReservaIfNotFound(String nombre) {
        if (!estadoReservaRepository.existsByNombre(nombre)) {
            estadoReservaRepository.save(new EstadoReserva(nombre));
        }
    }

    private Cliente insertClienteIfNotFound(String nombre, String documento, String telefono, String email) {
        if (!clienteRepository.existsByDocumento(documento)) {
            return clienteRepository.save(new Cliente(nombre, documento, telefono, email));
        }
        return clienteRepository.findAll().stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }
}
