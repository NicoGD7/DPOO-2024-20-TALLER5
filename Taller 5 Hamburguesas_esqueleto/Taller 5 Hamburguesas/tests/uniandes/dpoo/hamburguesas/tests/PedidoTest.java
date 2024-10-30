package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uniandes.dpoo.hamburguesas.mundo.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PedidoTest {
	
	private Pedido pedido;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private Combo combo;
	
	
	@BeforeEach
    public void setUp() {
         pedido = new Pedido("Juan Pérez", "Calle Falsa 123");
         hamburguesa = new ProductoMenu("Hamburguesa", 10000);
         papas = new ProductoMenu("Papas Fritas", 5000);
         combo = new Combo("Combo Clasico", 0.4, List.of(hamburguesa, papas));
    }
	
	@Test
    void testAgregarProductoMenu() {
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        pedido.agregarProducto(hamburguesa);
        assertEquals(1, pedido.productos.size());
    }

    @Test
    void testGetPrecioTotalPedidoConProductoAjustado() {
        Pedido pedido2 = new Pedido("Ana Gómez", "Avenida Principal 55"); 
        pedido2.agregarProducto(hamburguesa);
        assertEquals(11900, pedido2.getPrecioTotalPedido());
    }
    
    @Test
    void testGetPrecioTotalPedido() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);
        assertEquals(17850, pedido.getPrecioTotalPedido());
    }

    @Test
    void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Mario Sanchez", "Carrera 7 # 15-30");
        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Cliente: Mario Sanchez"));
       
    }

    @Test
    void testGuardarFactura() throws IOException {
        Pedido pedido = new Pedido("María Sánchez", "Calle 20 # 1-23");
        File archivo = new File("factura.txt");
        pedido.guardarFactura(archivo);
        assertTrue(archivo.exists());
       
    }
}
