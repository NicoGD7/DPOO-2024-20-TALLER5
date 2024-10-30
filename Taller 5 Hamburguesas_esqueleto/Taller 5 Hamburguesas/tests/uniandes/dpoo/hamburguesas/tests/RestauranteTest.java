package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RestauranteTest {
	private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();

        try {
            restaurante.cargarInformacionRestaurante(
                    new File("C:\\Users\\nesan\\OneDrive - Universidad de los andes\\TERCER SEMESTRE\\DPOO\\TALLERES\\Taller 5\\Taller 5 Hamburguesas_esqueleto\\Taller 5 Hamburguesas\\data\\ingredientes.txt"),
                    new File("C:\\Users\\nesan\\OneDrive - Universidad de los andes\\TERCER SEMESTRE\\DPOO\\TALLERES\\Taller 5\\Taller 5 Hamburguesas_esqueleto\\Taller 5 Hamburguesas\\data\\menu.txt"),
                    new File("C:\\Users\\nesan\\OneDrive - Universidad de los andes\\TERCER SEMESTRE\\DPOO\\TALLERES\\Taller 5\\Taller 5 Hamburguesas_esqueleto\\Taller 5 Hamburguesas\\data\\combos.txt")
            );
        } catch (HamburguesaException | NumberFormatException | IOException e) {
            e.printStackTrace();
            fail("Error al cargar los datos de prueba");
        }
    }

    @Test
    public void testIniciarPedido() {
        try {
            restaurante.iniciarPedido("James Rodriguez", "Uniandes");
            
            assertNotNull(restaurante.getPedidoEnCurso());
            assertEquals("James Rodriguez", restaurante.getPedidoEnCurso().getNombreCliente());
        } catch (YaHayUnPedidoEnCursoException e) {
            fail("Se lanzó una excepción inesperada");
        }
    }
    @Test
    public void TestcerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        
        restaurante.iniciarPedido("Messi", "Patio Bonito");
        Pedido pedidoCurso = restaurante.getPedidoEnCurso();
        restaurante.cerrarYGuardarPedido();

        
        String nombreArchivo = "factura_" + pedidoCurso.getIdPedido() + ".txt";
        File archivoPedido = new File(nombreArchivo);
        assertTrue(archivoPedido.exists(), "El archivo del pedido no se creó");

        
    }
    @Test
    public void testGetMenuBase() {

        List<ProductoMenu> menuBase = restaurante.getMenuBase();
        
        assertFalse(menuBase.isEmpty(), "La lista de productos base está vacía");

        assertTrue(menuBase.stream().anyMatch(producto -> producto.getNombre().equals("corral")),
                       "No se encontró corral en el menú base");
        assertEquals(22, restaurante.getMenuBase().size());
    }
    
    @Test
    public void testGetMenuCombos() {
    	
        List<Combo> menuCombos = restaurante.getMenuCombos();

        assertFalse(menuCombos.isEmpty(), "La lista de combos está vacía");

        assertTrue(menuCombos.stream().anyMatch(combo -> combo.getNombre().equals("combo corral")),
                       "No se encontró el combo corral en el menú de combos");
        assertEquals(4, restaurante.getMenuCombos().size());
        
    }
    
    @Test
    public void testGetIngredientes() {

        List<Ingrediente> ingredientes = restaurante.getIngredientes();

        assertFalse(ingredientes.isEmpty(), "La lista de ingredientes está vacía");

        assertTrue(ingredientes.stream().anyMatch(ingrediente -> ingrediente.getNombre().equals("cebolla")),
                       "No se encontró la cebolla en la lista de ingredientes");
        assertEquals(15, restaurante.getIngredientes().size());
    }
    
}
