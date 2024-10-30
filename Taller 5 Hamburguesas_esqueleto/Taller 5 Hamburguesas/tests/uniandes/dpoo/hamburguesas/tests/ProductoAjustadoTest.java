package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private Ingrediente PlatanoMaduro, PapaSalada, CocaCola;
    private ProductoAjustado productoAjustado;
    

    @BeforeEach
    void setUp() {
        productoBase = new ProductoMenu("Mamona", 60000);
        PlatanoMaduro = new Ingrediente("Platano Maduro", 8000);
        PapaSalada = new Ingrediente("Papa Salada", 5000);
        CocaCola = new Ingrediente("CocaCola", 2000);
        productoAjustado = new ProductoAjustado(productoBase);
    }
    
    
    @Test
    void testNombre() 
    {
    	 assertEquals("Mamona", productoAjustado.getNombre(), "El nombre del plato ajustado no es el correcto" );
    }
    
    @Test
    void testPrecioConIngredientesAgregados() {
        productoAjustado.agregados.add(PlatanoMaduro);
        productoAjustado.agregados.add(PapaSalada);
        assertEquals(73000, productoAjustado.getPrecio(), "El precio con ingredientes agregados es incorrecto");
    }

    @Test
    void testPrecioSinCambiosAlEliminarIngrediente() {
    	productoAjustado.agregados.add(PlatanoMaduro);
    	productoAjustado.eliminados.add(PapaSalada);
    	
        assertEquals(68000, productoAjustado.getPrecio(), "El precio con ingredientes eliminados es incorrecto");
    }

    @Test
    void testGenerarTextoFacturaConModificaciones() {
    	productoAjustado.agregados.add(PlatanoMaduro);
        productoAjustado.agregados.add(PapaSalada);
        productoAjustado.eliminados.add(CocaCola);
        
        String textoEsperado = "Mamona    +Platano Maduro                8000    +Papa Salada                5000    -CocaCola            73000\n";
        assertEquals(textoEsperado , productoAjustado.generarTextoFactura() , "El formato del texto de la factura no es el correcto");
    }
}