package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Combo;

public class ComboTest {
	private ProductoMenu hamburguesa, papas, gaseosa;
    private Combo combo1 , combo2;

    @BeforeEach
    void setUp() {
    	hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        papas = new ProductoMenu("Papas Fritas", 5000);
        gaseosa = new ProductoMenu("Gaseosa", 3000);
        combo1 = new Combo("Combo Clasico", 0.4, List.of(hamburguesa, papas));
        combo2 = new Combo("Cajita Feliz", 0.3, List.of(hamburguesa, papas , gaseosa));
    }

    @Test
    void testObtenerNombre() {
        assertEquals("Cajita Feliz", combo2.getNombre());
    }

    @Test
    void testCalcularPrecio() {
        assertEquals(9000, combo1.getPrecio() );
        //Encontré el error :)
    }

    @Test
    public void testGenerarTextoFactura() {
        Combo combo = new Combo("Combo Clasico", 0.2, List.of(hamburguesa, papas));
        String factura = combo.generarTextoFactura();

        String facturaEsperada = "Combo Combo Clasico\n" +
                                " Descuento: 0.2\n" +
                                "            12000\n";

        assertEquals(facturaEsperada, factura);
    }
	
	
}
