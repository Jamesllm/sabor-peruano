package com.example.sabor_peruano.config;

import com.example.sabor_peruano.model.Platillo;
import com.example.sabor_peruano.repository.PlatilloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PlatilloRepository platilloRepository;

    public DataInitializer(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        insertIfNotFound("Ceviche Clásico", "Delicioso ceviche clásico de pescado marinado en limón con cebolla y ají limo.", new BigDecimal("48.00"), "Entrada", "ceviche.jpg", true);
        insertIfNotFound("Lomo Saltado", "Trozos de lomo de res saltados con cebolla, tomate, ají amarillo y papas fritas.", new BigDecimal("55.00"), "Fondo", "lomo-saltado.jpg", true);
        insertIfNotFound("Ají de Gallina", "Crema de ají amarillo con gallina deshilachada, papas y huevo duro.", new BigDecimal("42.00"), "Fondo", "aji-de-gallina.jpg", true);
        insertIfNotFound("Anticuchos", "Brochetas de corazón de res marinado en ají panca acompañadas de papas doradas y choclo.", new BigDecimal("38.00"), "Entrada", "anticuchos.jpg", true);
        insertIfNotFound("Tacu Tacu con Lomo", "Mezcla de arroz y frejoles dorada a la sartén con lomo al jugo.", new BigDecimal("58.00"), "Fondo", "tacu-tacu.jpg", true);
        insertIfNotFound("Suspiro a la Limeña", "Postre tradicional limeño dulce y cremoso con merengue al oporto.", new BigDecimal("18.00"), "Postre", "suspiro-limena.jpg", true);
        insertIfNotFound("Pisco Sour", "El cóctel bandera del Perú, preparado con pisco, limón, jarabe de goma y clara de huevo.", new BigDecimal("28.00"), "Bebida", "pisco-sour.jpg", true);
        insertIfNotFound("Chicha Morada", "Bebida tradicional peruana hecha de maíz morado hervido con piña, manzana y especias.", new BigDecimal("12.00"), "Bebida", "chicha-morada.jpg", true);

        // Nuevos platos basados en las imágenes del proyecto
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
    }

    private void insertIfNotFound(String nombre, String descripcion, BigDecimal precio, String categoria, String urlImagen, boolean disponibilidad) {
        if (!platilloRepository.existsByNombre(nombre)) {
            platilloRepository.save(new Platillo(null, nombre, descripcion, precio, categoria, urlImagen, disponibilidad));
        }
    }
}
