package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

	private ProductoMenu producto ;
	
	@BeforeEach
	void setUp( ) throws Exception{
		producto = new ProductoMenu("Salchipapa" , 50000);
	}
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	@Test
	void testProductoMenu( ) 
	{
		assertEquals("Salchipapa" , producto.getNombre() , "El nombre del producto no es el correcto");
		assertEquals(50000, producto.getPrecio(), "El precio del producto no es el correcto");
	}
	
	@Test
    void testGenerarTextoFactura() {
        String textoEsperado = "Salchipapa\n            50000\n";
        assertEquals(textoEsperado, producto.generarTextoFactura(), "El formato del texto de la factura no es el correcto");
    }
	
}
